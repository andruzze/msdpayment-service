package ru.msd.msdapi.exception.number;

public class NotFoundCarNumberException extends RuntimeException {
    public NotFoundCarNumberException(String message) {
        super(message);
    }
}
