package ru.msd.msdapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.msd.msdapi.exception.user.*;

import java.util.ArrayList;
import java.util.Collection;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Collection<String>> handleValidationException(MethodArgumentNotValidException ex) {
        final Collection<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ToManyCarsException.class)
    public ResponseEntity<ToManyCarsException.Response> handleToManyCarsException(ToManyCarsException ex) {
        return new ResponseEntity<>(ex.toErrorResponse(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTelegramIdException.class)
    public ResponseEntity<String> handleUserExceptions(InvalidTelegramIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyToPayException.class)
    public ResponseEntity<String> handleNotEnoughMoneyException(NotEnoughMoneyToPayException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

