package ru.msd.msdapi.service.transit;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.msd.msdapi.api.dto.TransitDto;
import ru.msd.msdapi.converter.CarNumberConverter;
import ru.msd.msdapi.converter.TransitConverter;
import ru.msd.msdapi.exception.user.TransitNotFoundExceprion;
import ru.msd.msdapi.model.CarNumber;
import ru.msd.msdapi.model.Transit;
import ru.msd.msdapi.model.type.TransitStatus;
import ru.msd.msdapi.repository.CarNumberRepository;
import ru.msd.msdapi.repository.TransitRepository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TransitServiceImpl implements TransitService {
    private final TransitRepository transitRepository;
    private final CarNumberRepository numberRepository;
    private final TransitConverter transitConverter;

    @Autowired
    public TransitServiceImpl(TransitRepository transitRepository, CarNumberRepository numberRepository, TransitConverter transitConverter, CarNumberConverter carNumberConverter) {
        this.transitRepository = transitRepository;
        this.numberRepository = numberRepository;
        this.transitConverter = transitConverter;
    }

    @Override
    public UUID updateStatus(UUID transitId, TransitStatus status) {
        final Transit transit = this.transitRepository.findById(transitId).orElse(null);
        if (transit == null) {
            throw new TransitNotFoundExceprion(transitId);
        }
        transit.setStatus(TransitStatus.PAID);
        this.transitRepository.save(transit);
        return transit.getId();
    }

    @Override
    @Transactional
    public UUID addTransit(TransitDto transitDto) throws Exception {
        final Transit transit = this.transitConverter.convertFromWithNumber(
                transitDto,
                this.getOrCreateNumber(transitDto.numberDto().number())
        );
        transit.setStatus(TransitStatus.UNPAID);
        this.transitRepository.save(transit);
        return transit.getId();

    }

    @Override
    public TransitDto findByAccrualNumber(String accrualNumber) {
        final Transit transit = this.transitRepository.findByAccrualNumber(accrualNumber).orElse(null);
        if (transit == null) {
            throw new TransitNotFoundExceprion(accrualNumber);
        }
        return this.transitConverter.convert(transit);
    }

    @Override
    public Collection<TransitDto> findByCarNumber(String number) {
        final CarNumber carNumber = this.numberRepository.getNumber(number).orElse(null);
        if (carNumber == null) {
            return Set.of();
        }
        return this.transitRepository.findByCarNumber(carNumber.getId()).stream().map(this.transitConverter::convert).collect(Collectors.toList());
    }

    private CarNumber getOrCreateNumber(String number) {
        return this.numberRepository.getNumber(number).orElseGet(
                () -> this.numberRepository.save(new CarNumber(null, number))
        );
    }
}
