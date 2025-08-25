package ru.msd.msd.agent.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SendMessageDto(
        @JsonProperty(value = "chat_id", required = true)
        String chatId,

        @JsonProperty(value = "text", required = true)
        String text,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "parse_mode")
        String parseMode,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty(value = "reply_markup")
        InlineKeyboardMarkupDto replyMurkup) {
}
