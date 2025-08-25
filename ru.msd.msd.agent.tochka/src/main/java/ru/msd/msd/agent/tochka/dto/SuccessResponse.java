package ru.msd.msd.agent.tochka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SuccessResponse(
        @JsonProperty(value = "Data", required = true)
        Data data,

        @JsonProperty(value = "Link", required = true)
        Link link,

        @JsonProperty(value = "Meta", required = true)
        Meta meta) {
    private record Data(
            @JsonProperty(value = "requestId", required = true)
            String requestId) {
    }

    private record Link(
            @JsonProperty(value = "self", required = true)
            String self) {
    }

    private record Meta(
            @JsonProperty(value = "totalPages", required = true)
            String totalPages) {
    }
}