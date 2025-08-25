package ru.msd.msd.agent.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record InlineKeyboardMarkupDto(
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "inline_keyboard")
        InlineKeyboardButtonDto[][] inlineKeyboard) {
}
