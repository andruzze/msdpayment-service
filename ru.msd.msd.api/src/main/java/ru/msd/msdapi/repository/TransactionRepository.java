package ru.msd.msdapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.msd.msdapi.model.Transaction;

import java.util.Set;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    @Query(value = "SELECT * FROM transactions WHERE user_id = :userId", nativeQuery = true)
    Set<Transaction> findByUserId(@Param(value = "userId") UUID userId);
}
