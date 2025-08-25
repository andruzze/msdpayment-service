package ru.msd.msd.agent.tochka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import ru.msd.msd.agent.tochka.TochkaAgent;

@Configuration
public class TochkaConfig {

    @Value("${tochka.bearer.token}")
    String tochkaBearer;

    @Bean
    public TochkaAgent tochkaAgent(WebClient.Builder webClient) {
        return new TochkaAgent(webClient, this.tochkaBearer);
    }
}