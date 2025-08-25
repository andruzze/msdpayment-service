package ru.msd.msdapi.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.msd.msdapi.model.type.TransactionType;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class Transaction extends AutoFillId {

    @ManyToOne
    private final User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private final TransactionType transactionType;

    @Column(name = "transaction_date")
    private final LocalDate transactionDate;

    @Column(name = "amount")
    private final Double amount;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BillingPayment billingPayments;

    public Transaction(User user, TransactionType transactionType, LocalDate transactionDate, Double amount) {
        this.user = user;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }
}
