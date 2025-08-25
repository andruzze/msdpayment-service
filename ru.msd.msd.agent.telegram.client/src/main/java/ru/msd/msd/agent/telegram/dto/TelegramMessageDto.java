package ru.msd.msd.agent.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramMessageDto(
        @JsonProperty(value = "message_id")
        Integer messageId,

        @JsonProperty(value = "text")
        String text) {
}
