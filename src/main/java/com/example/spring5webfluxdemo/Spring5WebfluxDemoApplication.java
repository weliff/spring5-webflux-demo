package com.example.spring5webfluxdemo;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.example.spring5webfluxdemo.model.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

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
}
