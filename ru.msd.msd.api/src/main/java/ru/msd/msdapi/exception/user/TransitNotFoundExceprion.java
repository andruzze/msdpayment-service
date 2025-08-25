package ru.msd.msdapi.exception.user;

import java.util.UUID;

public class TransitNotFoundExceprion extends RuntimeException {
    public TransitNotFoundExceprion(String accrualNumber) {
        super(String.format("Cant find transit with accrual number as %s", accrualNumber));
    }

    public TransitNotFoundExceprion(UUID id) {
        super(String.format("Cant find transit with id as %s", id));
    }

}
