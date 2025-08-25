package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import ru.msd.msdapi.model.type.TransitStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TransitDto(
        @JsonProperty(value = "id")
        UUID id,

        @NotBlank(message = "Name cant be empty")
        @JsonProperty(value = "name")
        String name,

        @NotNull
        @JsonProperty(value = "accrual_date")
        LocalDate accrualDate,

        @NotNull
        @Valid
        @JsonProperty(value = "vehicle_number")
        CarNumberDto numberDto,

        @JsonProperty(value = "accrual_number")
        String accrualNumber,

        @JsonProperty(value = "amount")
        Double amount,

        @JsonProperty(value = "status")
        TransitStatus status) {
}
