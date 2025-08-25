package ru.msd.msd.agent;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import ru.msd.msd.agent.exception.ClientException;

public class CommonAgent {
    protected final WebClient webClient;

    protected CommonAgent(WebClient webClient) {
        this.webClient = webClient;
    }

    protected <T, S extends WebClient.RequestHeadersSpec<S>> Mono<T> executeRequest(
            WebClient.RequestHeadersSpec<S> spec,
            Class<T> responseObject
    ) {
        return spec.retrieve().bodyToMono(responseObject);
    }

    ;

    protected <T, S extends WebClient.RequestHeadersSpec<S>> Mono<T> executeRequest(
            WebClient.RequestHeadersSpec<S> spec,
            Class<T> successType,
            Class<?> errorType
    ) {
        return spec.retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> handleException(errorType, response))
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> handleException(errorType, response))
                .bodyToMono(successType);
    }

    private static Mono<Throwable> handleException(Class<?> errorType, ClientResponse response) {
        return response.bodyToMono(errorType).flatMap(
                errorBody -> Mono.error(new ClientException("Request exception: ", errorBody))
        );
    }
}