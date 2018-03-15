package com.example.spring5webfluxdemo.router;

import com.example.spring5webfluxdemo.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import sun.misc.Request;

@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> orderRoute(OrderHandler orderHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/orders/hello"), orderHandler::hello
        );
    }

}
