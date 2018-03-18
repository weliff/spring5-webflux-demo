package com.example.spring5webfluxdemo;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.example.spring5webfluxdemo.model.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.SerializationUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
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

//	@Bean
//	public ApplicationRunner applicationRunner(MongoOperations mongo) {
//		return args-> {
//					mongo.save(new Product(1, "XPTO 1"));
//					mongo.save(new Product(2, "XPTO 2"));
//					mongo.save(new Product(3, "XPTO 3"));
//					mongo.save(new Product(4, "XPTO 4"));
//					mongo.save(new Product(5, "XPTO 5"));
//		};
//	}
}
