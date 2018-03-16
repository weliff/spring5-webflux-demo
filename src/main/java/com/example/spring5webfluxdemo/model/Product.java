package com.example.spring5webfluxdemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@NoArgsConstructor
@Getter
public class Product {

    @Id
    private String id;

    @NotNull
    private Integer sku;

    @NotNull
    private String name;

    public Product(Integer sku, String name) {
        this.sku = sku;
        this.name = name;
    }
}
