package ru.msd.msdapi.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import ru.msd.msdapi.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByTelegramId(long telegramId);

    @Query("SELECT r FROM User r " +
            "WHERE (:id IS NULL OR r.id = :id) " +
            "AND (:name IS NULL OR r.name = :name) " +
            "AND (:telegramId IS NULL OR r.telegramId = :telegramId) " +
            "AND (CAST(:startDate AS date) IS NULL OR r.registerDate >= :startDate) " +
            "AND (CAST(:endDate AS date) IS NULL OR r.registerDate <= :endDate) " +
            "AND (:carNumber IS NULL OR r.id IN (SELECT cr.user.id FROM CarRegister cr WHERE cr.carNumber.number = :carNumber))")
    List<User> searchUser(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("telegramId") Long telegramId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("carNumber") String carNumber);

    @Override
    List<User> findAll();
}
