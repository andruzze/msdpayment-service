package ru.msd.msd.agent.tochka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import ru.msd.msd.agent.CommonAgent;
import ru.msd.msd.agent.tochka.dto.CreatePayment;
import ru.msd.msd.agent.tochka.dto.ExceptionResponse;
import ru.msd.msd.agent.tochka.dto.SuccessResponse;


public class TochkaAgent extends CommonAgent {
    private static final String API_VERSION = "v1.0";
    private static final String BASE_URL = "https://enter.tochka.com/uapi/";

    @Autowired
    public TochkaAgent(WebClient.Builder webClientBuilder, String tochkaBearer) {
        super(webClientBuilder
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", tochkaBearer)).build()
        );
    }

    public Mono<SuccessResponse> createPayment(CreatePayment paymentRequest) {
        return this.executeRequest(
                this.webClient.post()
                        .uri("/payment/{apiVersion}/for-sign", API_VERSION)
                        .bodyValue(paymentRequest),
                SuccessResponse.class,
                ExceptionResponse.class
        );
    }
}