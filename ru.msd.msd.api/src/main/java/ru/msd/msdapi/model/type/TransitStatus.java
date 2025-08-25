package ru.msd.msdapi.model.type;

public enum TransitStatus {
    PAID("transit status is paid"),
    UNPAID("transit status is unpaid");

    private final String description;

    TransitStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
