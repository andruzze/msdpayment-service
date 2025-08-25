package ru.msd.msdapi.model.type;


public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
