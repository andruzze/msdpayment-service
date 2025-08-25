package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.UUID;

public record CarRegisterDto(
        @JsonProperty(value = "id")
        UUID id,

        @JsonProperty(value = "user")
        UserDto userDto,

        @Valid
        @JsonProperty(value = "car_number")
        CarNumberDto carNumberDto,

        @JsonProperty("register_date")
        LocalDate registerDate) {
}
