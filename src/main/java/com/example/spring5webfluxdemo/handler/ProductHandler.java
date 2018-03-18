package com.example.spring5webfluxdemo.handler;

import com.example.spring5webfluxdemo.handler.validator.RequestValidatorHandler;
import com.example.spring5webfluxdemo.model.Product;
import com.example.spring5webfluxdemo.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class ProductHandler {

    private ProductRepository productRepository;

    private RequestValidatorHandler requestValidatorHandler;

    public ProductHandler(ProductRepository productRepository, RequestValidatorHandler requestValidatorHandler) {
        this.productRepository = productRepository;
        this.requestValidatorHandler = requestValidatorHandler;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
//        return ServerResponse.ok().body(BodyInserters.fromObject("Hello World"));
        return Mono.error(new IllegalArgumentException());
    }


    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(BodyInserters.fromPublisher(productRepository.findAll().log().map(p -> p.getName()), String.class));
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

    public Mono<ServerResponse> save(ServerRequest request) {
        return requestValidatorHandler.requireValidBody( productMono -> productMono
                                .flatMap(productRepository::save)
                                .flatMap(this::createdResponse), request, Product.class).log();
    }

    private Mono<ServerResponse> createdResponse(Product p) {
        return ServerResponse.created(URI.create("/products/" + p.getSku())).build();
    }
}
