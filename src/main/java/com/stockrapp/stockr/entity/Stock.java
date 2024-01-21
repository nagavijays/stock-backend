package com.stockrapp.stockr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String name;
    private int quantity;
}
