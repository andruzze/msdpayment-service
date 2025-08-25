package ru.msd.msd.agent.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramMessageResponseDto(
        @JsonProperty(value = "ok")
        Boolean ok,

        @JsonProperty(value = "result")
        TelegramMessageDto result,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "description")
        String description,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "error_code")
        Integer errorCode) {
}
