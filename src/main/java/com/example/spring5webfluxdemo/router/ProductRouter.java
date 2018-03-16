package com.example.spring5webfluxdemo.router;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoute(ProductHandler productHandler) {
        return route(GET("/products/hello")
                    .and(accept(MediaType.APPLICATION_JSON)), productHandler::hello)
                .and(route(GET("/products"),
                        productHandler::findAll))
                .and(route(GET("/products/{id}"),
                        productHandler::findById));
//                .and(RouterFunctions.route(
//                        RequestPredicates.POST("")
//                ));
    }

}
