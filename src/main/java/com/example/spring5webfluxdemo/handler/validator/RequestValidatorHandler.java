package com.example.spring5webfluxdemo.handler.validator;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

@Component
public class RequestValidatorHandler {

    private Validator validator;

    public RequestValidatorHandler(Validator validator) {
        this.validator = validator;
    }

    public <T> Mono<ServerResponse> requireValidBody(Function<Mono<T>, Mono<ServerResponse>> block,
                                                     ServerRequest request, Class<T> bodyClass) {
        return request
                .bodyToMono(bodyClass)
                .flatMap(body -> {
                    Set<ConstraintViolation<T>> violations = validator.validate(body);
                    return violations.isEmpty() ? block.apply(Mono.just(body)) : badRequestResponse(violations);
                });
    }

    private <T> Mono<ServerResponse> badRequestResponse(Set<ConstraintViolation<T>> violations) {
        return badRequest()
                .body(Flux.fromIterable(violations).map(c -> {
                    return  new Error(c.getPropertyPath().toString() + " " + c.getMessage());
                }), Error.class);
    }

    @Getter
    class Error {

        private String message;

        public Error(String message) {
            this.message = message;
        }
    }
}
