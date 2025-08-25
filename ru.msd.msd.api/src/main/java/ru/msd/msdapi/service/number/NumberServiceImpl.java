package ru.msd.msdapi.service.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.msd.msdapi.api.dto.CarNumberDto;
import ru.msd.msdapi.api.dto.SearchNumberDto;
import ru.msd.msdapi.converter.CarNumberConverter;
import ru.msd.msdapi.model.User;
import ru.msd.msdapi.repository.CarNumberRepository;
import ru.msd.msdapi.repository.CarRegisterRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class NumberServiceImpl implements RegisterNumberService, CarNumberService {

    private final CarRegisterRepository registerRepository;
    private final CarNumberRepository carNumberRepository;
    private final CarNumberConverter carNumberConverter;

    @Autowired
    public NumberServiceImpl(CarRegisterRepository registerRepository, CarNumberRepository carNumberRepository, CarNumberConverter carNumberConverter) {
        this.registerRepository = registerRepository;
        this.carNumberRepository = carNumberRepository;
        this.carNumberConverter = carNumberConverter;
    }

    @Override
    public Collection<CarNumberDto> getCarNumbers(SearchNumberDto searchNumberDto) {
        return this.carNumberRepository.findCarNumbersByFilter(
                searchNumberDto.offset(),
                searchNumberDto.limit(),
                searchNumberDto.telegramId(),
                searchNumberDto.userId()
                ).stream().map(this.carNumberConverter::convert).collect(Collectors.toList()
        );
    }

    @Override
    public List<String> findNumberByTelegramId(long telegramId) {
        return registerRepository.findNumberByTelegramId(telegramId);
    }

    @Override
    public int deleteNumberRecord(UUID userId, UUID numberId) {
        return registerRepository.deleteRegisterRecord(userId, numberId);
    }

    @Override
    public List<User> findUsersByNumber(String number) {
        validateNumber(number);
        return registerRepository.findUsersByNumber(number);
    }


    private static void validateNumber(String number) {
        if (number.isBlank()) {
            throw new IllegalArgumentException("Number cant be empty");
        }
    }
}
