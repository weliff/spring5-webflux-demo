package com.example.spring5webfluxdemo.config;

import com.example.spring5webfluxdemo.Spring5WebfluxDemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;

@Configuration
public class WebConfig {

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebExceptionHandler handle(ObjectMapper objectMapper) throws JsonProcessingException {
        return (ServerWebExchange exchange, Throwable ex) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            try {
                Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Falha ao processar pedido");
                byte[] bytes = objectMapper.writeValueAsBytes(error);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return response.writeWith(Flux.just(buffer));
            } catch (JsonProcessingException ignored) {}
            return response.setComplete();
        } ;
    }

    @Getter
    @AllArgsConstructor
    public static class Error {
        private String code;
        private String message;
    }
}
