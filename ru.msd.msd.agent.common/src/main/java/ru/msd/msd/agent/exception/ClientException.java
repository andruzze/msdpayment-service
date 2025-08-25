package ru.msd.msd.agent.exception;

public class  ClientException extends RuntimeException {
    public ClientException(String message, Object responseObj) {
        super(String.format("%s\n%s", message, responseObj.toString()));
    }
}
