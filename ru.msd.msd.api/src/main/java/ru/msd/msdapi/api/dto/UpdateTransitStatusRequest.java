package ru.msd.msdapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

import ru.msd.msdapi.model.type.TransitStatus;

import java.util.UUID;

public record UpdateTransitStatusRequest(

        @NotNull
        @JsonProperty(value = "ID")
        UUID id,

        @NotNull
        @JsonProperty(value = "status")
        TransitStatus status) {
}
