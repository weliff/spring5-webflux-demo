package com.example.spring5webfluxdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@NoArgsConstructor
@Getter
public class Product {

    @JsonIgnore
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
