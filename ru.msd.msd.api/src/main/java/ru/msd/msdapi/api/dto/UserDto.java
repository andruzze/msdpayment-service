package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        @JsonProperty(value = "id")
        UUID id,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "telegramId")
        Long telegramId,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "username")
        String username,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "autoPayment")
        Boolean autoPayment,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "balance", defaultValue = "0")
        Double balance,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "registerDate")
        LocalDate registerDate) {
}
