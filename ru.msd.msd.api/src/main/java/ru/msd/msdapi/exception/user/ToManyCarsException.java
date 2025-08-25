package ru.msd.msdapi.exception.user;

public class ToManyCarsException extends RuntimeException {

    public ToManyCarsException(int limit) {
        super(String.format("Max numbers count is %s", limit));
    }

    public Response toErrorResponse() {
        return new Response(
                "vehicle_limit_exceeded",
                getMessage()
        );
    }

    public record Response(
            String error,
            String message
    ) {
    }
}
