package ru.msd.msdapi.service.number;

import ru.msd.msdapi.api.dto.CarNumberDto;
import ru.msd.msdapi.api.dto.SearchNumberDto;

import java.util.Collection;

public interface CarNumberService {

    Collection<CarNumberDto> getCarNumbers(SearchNumberDto searchNumberDto);
}
