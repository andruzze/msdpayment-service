package ru.msd.msdapi.service.transit;

import ru.msd.msdapi.api.dto.TransitDto;
import ru.msd.msdapi.model.type.TransitStatus;

import java.util.Collection;
import java.util.UUID;

public interface TransitService {
    UUID updateStatus(UUID transitId, TransitStatus status);

    UUID addTransit(TransitDto transitDto) throws Exception;

    TransitDto findByAccrualNumber(String accrualNumber);

    Collection<TransitDto> findByCarNumber(String number);
}

