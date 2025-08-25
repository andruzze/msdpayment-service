package ru.msd.msdapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.msd.msdapi.model.CarNumber;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CarNumberRepository extends CrudRepository<CarNumber, UUID> {

    @Query(value = "SELECT * FROM car_numbers WHERE number = :number", nativeQuery = true)
    Optional<CarNumber> getNumber(@Param(value = "number") String number);

    @Query(value = "SELECT * FROM car_numbers WHERE id = :id", nativeQuery = true)
    Optional<CarNumber> getNumber(@Param(value = "id") UUID id);

    @Query(value = "SELECT * FROM car_numbers " +
            "WHERE (:telegramId IS NULL OR id IN(SELECT number_id FROM car_registers WHERE user_id IN (SELECT id FROM users WHERE telegram_id = :telegramId)))" +
            "AND (:userId IS NULL OR id IN(SELECT number_id FROM car_registers WHERE user_id = :userId))" +
            "ORDER BY id LIMIT :limit " +
            "OFFSET :offset", nativeQuery = true)
    Collection<CarNumber> findCarNumbersByFilter(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("telegramId") Long telegramId,
            @Param("userId") UUID userId);
}
