package com.example.spring5webfluxdemo.router;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ProductHandler productHandler) {
        return RouterFunctions.
                route(
                        RequestPredicates.GET("/products/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), productHandler::hello)
                .and(RouterFunctions.route(
                        RequestPredicates.GET("/products"), productHandler::findAll))
                .and(RouterFunctions.route(
                        RequestPredicates.GET("/products/{id}"), productHandler::findById));
//                .and(RouterFunctions.route(
//                        RequestPredicates.POST("")
//                ));
    }

}
