package ru.msd.msdapi.service.user;

import ru.msd.msdapi.api.dto.CarRegisterDto;
import ru.msd.msdapi.api.dto.UserDto;
import ru.msd.msdapi.api.dto.UserFilterDto;

import java.util.Collection;
import java.util.UUID;

public interface UserService {
    UUID registerUser(UserDto userDto);

    Collection<UserDto> getAllUsers();

    Collection<UserDto> searchUsers(UserFilterDto dto);

    UUID updateUser(UUID uuid, String username, Double balance);

    UUID registerCarNumber(CarRegisterDto carRegisterDto);
}
