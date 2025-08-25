package ru.msd.msd.agent.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record InlineKeyboardButtonDto(
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "text")
        String text,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "url")
        String url,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "callback_data")
        String callbackData) {
}
