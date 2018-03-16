package com.example.spring5webfluxdemo;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.example.spring5webfluxdemo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.SerializationUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@EnableWebFlux
@SpringBootApplication
public class Spring5WebfluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5WebfluxDemoApplication.class, args);
	}


	@Bean
	public ApplicationRunner applicationRunner(MongoOperations mongo) {
		return args-> {
					mongo.save(new Product(1, "XPTO 1"));
					mongo.save(new Product(2, "XPTO 2"));
					mongo.save(new Product(3, "XPTO 3"));
					mongo.save(new Product(4, "XPTO 4"));
					mongo.save(new Product(5, "XPTO 5"));
		};
	}

	@Bean
    @Order(-2)
	public WebExceptionHandler handle() {
        return (exchange, ex) -> {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            exchange.getResponse().writeWith(Mono.just(wrap(new Error("Falha ao processar pedido")))).block();
            return exchange.getResponse().setComplete();
        } ;
    }

    private DataBuffer wrap(Object obj) {
        return new DefaultDataBufferFactory().wrap(SerializationUtils.serialize(obj));
    }

    @AllArgsConstructor
    @Data
    static class Error implements Serializable{
	    private String message;
    }
}
