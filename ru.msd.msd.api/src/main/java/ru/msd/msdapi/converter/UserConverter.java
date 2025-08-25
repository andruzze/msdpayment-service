package ru.msd.msdapi.converter;

import ru.msd.msdapi.api.dto.UserDto;
import ru.msd.msdapi.model.User;

public class UserConverter implements BiDirectConverter<User, UserDto> {

    @Override
    public User convertFrom(UserDto dto) {
        return new User(
                dto.id(),
                dto.username(),
                dto.telegramId(),
                dto.balance(),
                dto.registerDate());
    }

    @Override
    public UserDto convert(User obj) {
        return new UserDto(
                obj.getId(),
                obj.getTelegramId(),
                obj.getName(),
                obj.getAutoPayment(),
                obj.getBalance(),
                obj.getRegisterDate());
    }
}
