package com.example.spring5webfluxdemo.router;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.data.mongodb.core.aggregation.BooleanOperators.And.and;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoute(ProductHandler productHandler) {
        RouterFunction<ServerResponse> routes =
                route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), productHandler::hello)
                        .andRoute(GET("/{id}"), productHandler::findById)
                        .andRoute(method(GET), productHandler::findAll)
                        .andRoute(method(POST).and(accept(MediaType.APPLICATION_JSON)), productHandler::save);

        return nest(path("/products"), routes);

    }

}
