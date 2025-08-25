package ru.msd.msdapi.service.user;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.msd.msdapi.api.dto.CarRegisterDto;
import ru.msd.msdapi.api.dto.UserDto;
import ru.msd.msdapi.api.dto.UserFilterDto;
import ru.msd.msdapi.config.ApplicationProperties;
import ru.msd.msdapi.converter.CarRegisterConverter;
import ru.msd.msdapi.converter.UserConverter;
import ru.msd.msdapi.exception.user.InvalidTelegramIdException;
import ru.msd.msdapi.exception.user.ToManyCarsException;
import ru.msd.msdapi.exception.user.UserAlreadyExistException;
import ru.msd.msdapi.exception.user.UserNotFoundException;
import ru.msd.msdapi.model.CarNumber;
import ru.msd.msdapi.model.CarRegister;
import ru.msd.msdapi.model.User;
import ru.msd.msdapi.repository.CarNumberRepository;
import ru.msd.msdapi.repository.CarRegisterRepository;
import ru.msd.msdapi.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ApplicationProperties properties;
    private final UserRepository repository;
    private final UserConverter userConverter;
    private final CarNumberRepository carNumberRepository;
    private final CarRegisterRepository carRegisterRepository;
    private final CarRegisterConverter carRegisterConverter;

    @Autowired
    public UserServiceImpl(ApplicationProperties properties, UserRepository repository, UserConverter userConverter, CarNumberRepository carNumberRepository, CarRegisterRepository carRegisterRepository, CarRegisterConverter carRegisterConverter) {

        this.properties = properties;
        this.repository = repository;
        this.userConverter = userConverter;
        this.carNumberRepository = carNumberRepository;
        this.carRegisterRepository = carRegisterRepository;
        this.carRegisterConverter = carRegisterConverter;
    }

    @Override
    public Collection<UserDto> searchUsers(UserFilterDto dto) {
        final Collection<User> users = this.repository.searchUser(
                dto.id(),
                dto.name(),
                dto.telegramId(),
                dto.startDate(),
                dto.endDate(),
                dto.carNumber()
        );
        return users.stream().map(this.userConverter::convert).collect(Collectors.toList());
    }

    @Override
    public UUID registerUser(UserDto userDto) {
        final Long telegramId = userDto.telegramId();
        if (!validateTelegramId(telegramId)) {
            throw new InvalidTelegramIdException("Invalid telegram id");
        }

        final Optional<User> existUser = this.repository.findByTelegramId(userDto.telegramId());
        if (existUser.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with %s telegram_id is already exist", userDto.telegramId()));
        }
        final User user = this.userConverter.convertFrom(userDto);
        repository.save(user);
        return user.getId();
    }

    @Override
    public UUID updateUser(UUID uuid, String username, Double balance) {
        final User existUser = this.repository.findById(uuid).orElseThrow(
                () -> new UserNotFoundException((String.format("User with id: %s is not found", uuid)))
        );
        if (username != null) {
            existUser.setName(username);
        }
        if (balance != null) {
            existUser.setBalance(balance);
        }
        this.repository.save(existUser);
        return existUser.getId();
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        final Collection<User> users = repository.findAll();
        return users.stream().map(userConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UUID registerCarNumber(CarRegisterDto carRegisterDto) {
        final User user = this.repository.findById(carRegisterDto.userDto().id()).orElseThrow(() -> new UserNotFoundException("User not found"));
        final CarRegister carRegister = this.carRegisterConverter.convertFrom(carRegisterDto);
        final String number = carRegister.getCarNumber().getNumber();
        final CarNumber carNumber = this.carNumberRepository.getNumber(number).orElseGet(
                () -> this.carNumberRepository.save(carRegister.getCarNumber())
        );
        if (this.carRegisterRepository.findNumberByTelegramId(user.getTelegramId()).size() == this.properties.getCarRegistersLimit()) {
            throw new ToManyCarsException(this.properties.getCarRegistersLimit());
        }
        return carRegisterRepository.getCarRegisterByUserIdAndNumberId(user.getId(), carNumber.getId()).orElseGet(
                () -> {
                    carRegister.setCarNumber(carNumber);
                    this.carRegisterRepository.save(carRegister);
                    return carRegister.getId();
                }
        );
    }

    private static boolean validateTelegramId(Long telegramId) {
        return telegramId != null && telegramId > 0;
    }
}
