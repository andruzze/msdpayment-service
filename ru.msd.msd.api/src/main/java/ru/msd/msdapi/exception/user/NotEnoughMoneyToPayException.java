package ru.msd.msdapi.exception.user;

public class NotEnoughMoneyToPayException extends RuntimeException {
    public NotEnoughMoneyToPayException(double currentBalance, double amount) {
        super(String.format("NotEnoughMoneyToPayException. Current balance is %s, needed %s", currentBalance, amount));
    }
}
