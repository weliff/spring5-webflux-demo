package com.example.spring5webfluxdemo.handler.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.util.function.Function;

@Component
public class RequestValidatorHandler {

    private Validator validator;

    public RequestValidatorHandler(Validator validator) {
        this.validator = validator;
    }

    public <T> Mono<ServerResponse> requireValidBody( Function<Mono<T>, Mono<ServerResponse>> block,
                                                      ServerRequest request, Class<T> bodyClass) {
        return request
                .bodyToMono(bodyClass)
                .flatMap(body -> validator.validate(body).isEmpty()
                                ? block.apply(Mono.just(body))
                                : ServerResponse.unprocessableEntity().build());
    }
}
