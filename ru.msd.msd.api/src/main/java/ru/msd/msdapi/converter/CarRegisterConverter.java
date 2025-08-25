package ru.msd.msdapi.converter;

import org.springframework.beans.factory.annotation.Autowired;

import ru.msd.msdapi.api.dto.CarNumberDto;
import ru.msd.msdapi.api.dto.CarRegisterDto;
import ru.msd.msdapi.api.dto.UserDto;
import ru.msd.msdapi.model.CarNumber;
import ru.msd.msdapi.model.CarRegister;
import ru.msd.msdapi.model.User;

public class CarRegisterConverter implements BiDirectConverter<CarRegister, CarRegisterDto> {

    private final CarNumberConverter carNumberConverter;
    private final UserConverter userConverter;

    @Autowired
    public CarRegisterConverter(CarNumberConverter carNumberConverter, UserConverter userConverter) {
        this.carNumberConverter = carNumberConverter;
        this.userConverter = userConverter;
    }

    @Override
    public CarRegister convertFrom(CarRegisterDto dto) {
        final CarNumber number = this.carNumberConverter.convertFrom(dto.carNumberDto());
        final User user = this.userConverter.convertFrom(dto.userDto());
        return new CarRegister(
                dto.id(),
                user,
                number,
                dto.registerDate()
        );
    }

    @Override
    public CarRegisterDto convert(CarRegister obj) {
        final UserDto userDto = this.userConverter.convert(obj.getUser());
        final CarNumberDto numberDto = this.carNumberConverter.convert(obj.getCarNumber());
        return new CarRegisterDto(
                obj.getId(),
                userDto,
                numberDto,
                obj.getRegisterDate()
        );
    }
}
