package ru.msd.msdapi.converter;

import org.springframework.beans.factory.annotation.Autowired;

import ru.msd.msdapi.api.dto.TransitDto;
import ru.msd.msdapi.model.CarNumber;
import ru.msd.msdapi.model.Transit;

public class TransitConverter implements BiDirectConverter<Transit, TransitDto> {

    private final CarNumberConverter carNumberConverter;

    @Autowired
    public TransitConverter(CarNumberConverter carNumberConverter) {
        this.carNumberConverter = carNumberConverter;
    }

    @Override
    public Transit convertFrom(TransitDto dto) {
        final CarNumber number = this.carNumberConverter.convertFrom(dto.numberDto());
        return new Transit(
                dto.id(),
                dto.name(),
                number,
                dto.accrualNumber(),
                dto.accrualDate(),
                dto.amount()
        );
    }

    @Override
    public TransitDto convert(Transit obj) {
        return new TransitDto(
                obj.getId(),
                obj.getName(),
                obj.getAccrualDate(),
                this.carNumberConverter.convert(obj.getCarNumber()),
                obj.getAccrual_number(),
                obj.getAmount(),
                obj.getStatus()
        );
    }

    public Transit convertFromWithNumber(TransitDto dto, CarNumber carNumber) {
        return new Transit(
                dto.id(),
                dto.name(),
                carNumber,
                dto.accrualNumber(),
                dto.accrualDate(),
                dto.amount()
        );
    }
}
