package com.stockrapp.stockr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String stockId;
    private String type;
    private int quantity;
}
