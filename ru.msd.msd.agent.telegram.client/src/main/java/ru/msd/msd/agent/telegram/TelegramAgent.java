package ru.msd.msd.agent.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import ru.msd.msd.agent.CommonAgent;
import ru.msd.msd.agent.telegram.dto.SendMessageDto;
import ru.msd.msd.agent.telegram.dto.TelegramMessageResponseDto;

public class TelegramAgent extends CommonAgent {
    private static final String TELEGRAM_BASE_URL = "https://api.telegram.org";
    private final String token;

    @Autowired
    public TelegramAgent(WebClient.Builder webClient, String token) {
        super(webClient.baseUrl(TELEGRAM_BASE_URL).build());
        this.token = token;
    }

    public Mono<TelegramMessageResponseDto> sendMessage(SendMessageDto sendMessageDto) {
        return executeRequest(
                webClient.post()
                        .uri("/bot{token}/sendMessage", this.token)
                        .bodyValue(sendMessageDto),
                TelegramMessageResponseDto.class
        );
    }
}

