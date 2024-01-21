package com.stockrapp.stockr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "stocks")
@ToString
public class Stock {
    @Id
    private String id;
    private String companyName;
    private String symbol;

    public Stock(String companyName, String symbol) {
        this.companyName = companyName;
        this.symbol = symbol;
    }


}
