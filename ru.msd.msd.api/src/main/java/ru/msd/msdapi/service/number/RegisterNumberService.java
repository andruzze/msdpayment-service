package ru.msd.msdapi.service.number;

import ru.msd.msdapi.model.User;

import java.util.List;
import java.util.UUID;

public interface RegisterNumberService {
    List<String> findNumberByTelegramId(long telegramId);

    int deleteNumberRecord(UUID userId, UUID numberId);

    List<User> findUsersByNumber(String number);
}
