package ru.msd.msd.agent.config;

import io.netty.channel.ConnectTimeoutException;
import io.netty.handler.timeout.ReadTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import javax.naming.ServiceUnavailableException;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create().responseTimeout(Duration.ofSeconds(30))
                        )
                )
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(this.retryFilter())
                .filter(this.loggingFilter());
    }

    private ExchangeFilterFunction retryFilter() {
        return (request, next) -> next.exchange(request)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .maxBackoff(Duration.ofSeconds(10))
                        .jitter(0.5)
                        .filter(this::shouldRetry)
                        .doBeforeRetry(retrySignal -> log.warn(
                                "Retry attempt #{} for request {} {}",
                                retrySignal.totalRetries() + 1,
                                retrySignal.failure().getClass().getSimpleName(),
                                request.url()))
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new ServiceUnavailableException("Unavailable destination service with"))
                );
    }

    private ExchangeFilterFunction loggingFilter() {
        return (clientRequest, next) -> {
            long startTime = System.currentTimeMillis();
            log.info("Outgoing request: {} {} {}",
                    clientRequest.method(),
                    clientRequest.url(),
                    clientRequest.headers());

            return next.exchange(clientRequest)
                    .doOnError(ex -> {
                        long duration = System.currentTimeMillis() - startTime;
                        log.error("Request failed: {} {} - {}ms - Error: {}",
                                clientRequest.method(),
                                clientRequest.url(),
                                duration,
                                ex.getMessage());
                    })
                    .doOnSuccess((response) -> {
                        long duration = System.currentTimeMillis() - startTime;
                        log.info("Response received: {} {} - {}ms - Status: {}",
                                clientRequest.method(),
                                clientRequest.url(),
                                duration,
                                response.statusCode());
                    });
        };
    }

    private boolean shouldRetry(Throwable throwable) {
        return throwable instanceof ConnectTimeoutException ||
                throwable instanceof ReadTimeoutException ||
                (throwable instanceof WebClientResponseException &&
                        ((WebClientResponseException) throwable).getStatusCode().is5xxServerError());
    }
}
