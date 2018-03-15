package com.example.spring5webfluxdemo.handler;

import com.example.spring5webfluxdemo.model.Product;
import com.example.spring5webfluxdemo.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.time.Duration;

@Component
public class ProductHandler {

    private ProductRepository productRepository;

    public ProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromObject("Hello World"));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        request.principal();
        Flux<String> publisher = Flux.<String>create(emitter -> {
            emitter.next("Test");
            emitter.next("Test");
            emitter.next("Test");
            emitter.next("Test");
            emitter.complete();
        });

        return ServerResponse
                .ok()
                .body(BodyInserters.fromPublisher(productRepository.findAll().log(), Product.class));
    }


    public Mono<ServerResponse> findById(ServerRequest request) {
        request.pathVariable("id");
        return ServerResponse.ok().body(
                BodyInserters.fromPublisher(
                        productRepository.findBySku(Integer.valueOf(request.pathVariable("id"))),
                        Product.class
                )
        );
    }
}
