package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.msd.msdapi.api.dto.constraint.ValidCarNumber;

import java.util.UUID;

public record CarNumberDto(

        @JsonProperty(value = "id")
        UUID uuid,

        @ValidCarNumber
        @JsonProperty(value = "number")
        String number) {
}
