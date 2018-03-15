package com.example.spring5webfluxdemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;

    private Integer sku;

    private String name;

    public Product(Integer sku, String name) {
        this.sku = sku;
        this.name = name;
    }
}
