package com.example.spring5webfluxdemo.repository;

import com.example.spring5webfluxdemo.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    public Mono<Product> findBySku(Integer sku);

//    Flux<Product> products = Flux.just(
//            new Product(1, "XPTO 1"),
//            new Product(2, "XPTO 2"),
//            new Product(3, "XPTO 3"),
//            new Product(4, "XPTO 4"),
//            new Product(5, "XPTO 5")
//    );
//
//    public Mono<Product> findBySku(Integer sku) {
//        return products.filter(p -> p.getSku().equals(sku)).single();
//    }


}
