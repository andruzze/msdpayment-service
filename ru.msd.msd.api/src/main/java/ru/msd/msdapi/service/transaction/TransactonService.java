package ru.msd.msdapi.service.transaction;

import ru.msd.msdapi.api.dto.TransactionDto;

import java.util.Collection;
import java.util.UUID;

public interface TransactonService {
    Collection<TransactionDto> getTransactionByUserId(UUID id);

    UUID createTransaction(TransactionDto transactionDto) throws Exception;


}
