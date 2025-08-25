package ru.msd.msdapi.repository;

import org.springframework.data.repository.CrudRepository;

import ru.msd.msdapi.model.BillingPayment;

import java.util.UUID;

public interface BillingPaymentRepository extends CrudRepository<BillingPayment, UUID> {
}
