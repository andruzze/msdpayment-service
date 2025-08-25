package ru.msd.msdapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ru.msd.msdapi.model.CarNumber;
import ru.msd.msdapi.model.CarRegister;
import ru.msd.msdapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRegisterRepository extends CrudRepository<CarRegister, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM CarNumber cn WHERE cn.number = :carNum")
    void deleteNumber(@Param("carNum") String carNum);

    @Query("SELECT cr.carNumber.number FROM CarRegister cr WHERE cr.user.telegramId = :telegramId")
    List<String> findNumberByTelegramId(@Param("telegramId") long telegramId);

    @Query("SELECT cr.user FROM CarRegister cr WHERE cr.carNumber.number = :number")
    List<User> findUsersByNumber(@Param("number") String number);

    @Modifying
    @Transactional
    @Query("DELETE FROM CarRegister cr WHERE cr.user.id = :userId AND cr.carNumber.id = :numberId")
    int deleteRegisterRecord(@Param("userId") UUID userId, @Param("numberId") UUID numberId);

    @Query("SELECT cr.id FROM CarRegister cr WHERE cr.user.id = :userId AND cr.carNumber.id = :carNumberId")
    Optional<UUID> getCarRegisterByUserIdAndNumberId(@Param("userId") UUID userId, @Param("carNumberId") UUID carNumberId);
}
