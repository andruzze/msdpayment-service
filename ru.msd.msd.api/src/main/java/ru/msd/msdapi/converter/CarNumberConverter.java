package ru.msd.msdapi.converter;

import ru.msd.msdapi.api.dto.CarNumberDto;
import ru.msd.msdapi.model.CarNumber;

public class CarNumberConverter implements BiDirectConverter<CarNumber, CarNumberDto> {
    @Override
    public CarNumber convertFrom(CarNumberDto dto) {
        return new CarNumber(
                dto.uuid(),
                dto.number()
        );
    }

    @Override
    public CarNumberDto convert(CarNumber obj) {
        return new CarNumberDto(
                obj.getId(),
                obj.getNumber()
        );
    }
}
