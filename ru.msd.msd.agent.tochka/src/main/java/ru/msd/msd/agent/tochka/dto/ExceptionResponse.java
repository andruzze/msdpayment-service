package ru.msd.msd.agent.tochka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExceptionResponse(
        @JsonProperty(value = "code", required = true)
        String code,

        @JsonProperty(value = "id", required = true)
        String id,

        @JsonProperty(value = "message", required = true)
        String message,

        @JsonProperty(value = "Errors", required = true)
        List<Errors> errors) {

    @Override
    public String toString() {
        return String.format("TochkaError[code=%s, message=%s, errors=%s]",
                code, message, errors);
    }

    private record Errors(
            @JsonProperty(value = "errorCode", required = true)
            String errorCode,

            @JsonProperty(value = "message", required = true)
            String message,

            @JsonProperty(value = "url", required = true)
            String url) {
    }
}