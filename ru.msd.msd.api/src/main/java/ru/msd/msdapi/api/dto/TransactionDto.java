package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import ru.msd.msdapi.model.type.TransactionType;

import java.util.UUID;

public record TransactionDto(
        @JsonProperty(value = "id")
        UUID uuid,

        @JsonProperty(value = "user_id")
        UUID userId,

        @NotNull
        @JsonProperty(value = "amount")
        Double amount,

        @NotBlank
        @JsonProperty(value = "type")
        TransactionType type,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty(value = "transit")
        TransitDto transitDto) {
}
