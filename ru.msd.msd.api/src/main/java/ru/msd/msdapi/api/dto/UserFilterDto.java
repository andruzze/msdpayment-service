package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public record UserFilterDto(
        @JsonProperty(value = "id")
        UUID id,

        @JsonProperty(value = "username")
        String name,

        @JsonProperty("telegram_id")
        Long telegramId,

        @JsonProperty(value = "start_register_date")
        LocalDate startDate,

        @JsonProperty(value = "end_register_filter")
        LocalDate endDate,

        @JsonProperty(value = "car_number")
        String carNumber) {
}
