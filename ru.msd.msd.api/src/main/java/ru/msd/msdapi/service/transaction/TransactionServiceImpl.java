package ru.msd.msdapi.service.transaction;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.msd.msdapi.api.dto.TransactionDto;
import ru.msd.msdapi.converter.TransactionConverter;
import ru.msd.msdapi.exception.transaction.CreateTransactionException;
import ru.msd.msdapi.exception.user.NotEnoughMoneyToPayException;
import ru.msd.msdapi.exception.user.TransitAlreadyPaidException;
import ru.msd.msdapi.exception.user.TransitNotFoundExceprion;
import ru.msd.msdapi.exception.user.UserNotFoundException;
import ru.msd.msdapi.model.BillingPayment;
import ru.msd.msdapi.model.Transaction;
import ru.msd.msdapi.model.Transit;
import ru.msd.msdapi.model.User;
import ru.msd.msdapi.model.type.TransactionType;
import ru.msd.msdapi.model.type.TransitStatus;
import ru.msd.msdapi.repository.BillingPaymentRepository;
import ru.msd.msdapi.repository.TransactionRepository;
import ru.msd.msdapi.repository.TransitRepository;
import ru.msd.msdapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactonService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransitRepository transitRepository;
    private final BillingPaymentRepository billingPaymentRepository;
    private final TransactionConverter transactionConverter;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, TransitRepository transitRepository, BillingPaymentRepository billingPaymentRepository, TransactionConverter transactionConverter) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transitRepository = transitRepository;
        this.billingPaymentRepository = billingPaymentRepository;
        this.transactionConverter = transactionConverter;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UUID createTransaction(TransactionDto transactionDto) throws Exception {
        try {
            if (transactionDto.transitDto() == null && transactionDto.type() == TransactionType.DEPOSIT) {
                return this.createDeposit(transactionDto.userId(), transactionDto.amount());
            } else if (transactionDto.transitDto() != null && transactionDto.type() == TransactionType.WITHDRAW) {
                return this.createWithdraw(transactionDto.userId(), transactionDto.amount(), transactionDto.transitDto().accrualNumber());
            }
        } catch (Exception e) {
            throw new CreateTransactionException("Failed to create transaction: " + e.getMessage());
        }
        throw new CreateTransactionException("Invalid transaction type or parameters");
    }

    @Override
    public Collection<TransactionDto> getTransactionByUserId(UUID id) {
        final Collection<Transaction> transactions = this.transactionRepository.findByUserId(id);
        return transactions.stream().map(this.transactionConverter::convert).collect(Collectors.toList());
    }

    private UUID createDeposit(UUID userId, Double amount) {
        final Transaction transaction = this.initTransaction(userId, amount, TransactionType.DEPOSIT);
        return transaction.getId();
    }

    private UUID createWithdraw(UUID userId, Double amount, String accrualNumber) {
        final Transaction transaction = this.initTransaction(userId, amount, TransactionType.WITHDRAW);
        final Transit transit = this.transitRepository.findByAccrualNumber(accrualNumber).orElse(null);
        if (transit == null) {
            throw new TransitNotFoundExceprion(accrualNumber);
        }
        if (transit.getStatus() == TransitStatus.PAID) {
            throw new TransitAlreadyPaidException(accrualNumber);
        }
        transit.setStatus(TransitStatus.PAID);
        final BillingPayment payment = new BillingPayment();
        payment.setTransaction(transaction);
        payment.setTransit(transit);
        this.billingPaymentRepository.save(payment);
        this.transitRepository.save(transit);
        return transaction.getId();
    }

    private Transaction initTransaction(UUID userId, Double amount, TransactionType type) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Transaction amount mast be greater then 0");
        }
        final User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User id: ", userId)
        );
        final Transaction transaction = new Transaction(user, type, LocalDate.now(), amount);
        this.transactionRepository.save(transaction);

        if (type == TransactionType.DEPOSIT) {
            setUserBalance(user, amount);
        } else {
            setUserBalance(user, -amount);
        }
        this.userRepository.save(user);

        return transaction;
    }

    private static void setUserBalance(User user, double amount) {
        final double currentBalance = user.getBalance();
        if (currentBalance + amount < 0) {
            throw new NotEnoughMoneyToPayException(currentBalance, amount);
        }
        user.setBalance(currentBalance + amount);
    }
}
