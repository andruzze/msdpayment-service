package ru.msd.msdapi.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String paramName, Object findParam) {
        super(String.format("Not found user with %s as %s", paramName, findParam));
    }
}
