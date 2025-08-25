package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SearchNumberDto(
        @NotNull
        @JsonProperty(value = "offset")
        Integer offset,

        @NotNull
        @JsonProperty(value = "limit")
        Integer limit,

        @JsonProperty(value = "telegramId")
        Long telegramId,

        @JsonProperty(value = "userId")
        UUID userId) {
}
