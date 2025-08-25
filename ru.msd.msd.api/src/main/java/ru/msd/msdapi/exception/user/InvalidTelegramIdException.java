package ru.msd.msdapi.exception.user;

public class InvalidTelegramIdException extends RuntimeException {
    public InvalidTelegramIdException(String message) {
        super(message);
    }
}
