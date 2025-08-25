package ru.msd.msd.agent.telegram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import ru.msd.msd.agent.telegram.TelegramAgent;

@Configuration
public class TelegramConfig {

    @Value("${telegram.bot.token}")
    public String token;

    @Bean
    public TelegramAgent telegramAgent(WebClient.Builder webClient) {
        return new TelegramAgent(webClient, this.token);
    }
}
