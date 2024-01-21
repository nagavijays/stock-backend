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
    private String userId;
    private String orderType; // Buy or sell
    private int quantity;
    private double price;
    
    public Order(String id, String stockId, String userId, String orderType, int quantity, double price) {
        this.id = id;
        this.stockId = stockId;
        this.userId = userId;
        this.orderType = orderType;
        this.quantity = quantity;
        this.price = price;
    }


}
