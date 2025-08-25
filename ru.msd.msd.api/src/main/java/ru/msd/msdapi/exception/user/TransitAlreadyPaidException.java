package ru.msd.msdapi.exception.user;

public class TransitAlreadyPaidException extends RuntimeException {
    public TransitAlreadyPaidException(String accrualNumber) {
        super(String.format("Transit with accrual number : %s already paid", accrualNumber));
    }
}
