package ru.msd.msdapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.msd.msdapi.model.Transit;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TransitRepository extends CrudRepository<Transit, UUID> {

    @Query(value = "SELECT * FROM transits WHERE accrual_number = :accrual_number", nativeQuery = true)
    Optional<Transit> findByAccrualNumber(@Param(value = "accrual_number") String accrualNumber);

    @Query(value = "SELECT * FROM transits WHERE car_number_id = :number_id", nativeQuery = true)
    Set<Transit> findByCarNumber(@Param(value = "number_id") UUID numberId);
}
