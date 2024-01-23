package com.stockrapp.stockr.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "trades")
@ToString
public class TransactionHistory {

    @Id
    private String transactionId;
    private String buyerOrderId;
    private String sellerOrderId;
    private String stockId;
    private double buyPrice; // Should use java.util.Currency? TODO:
    private double sellPrice; // Is it required to store transacted price twice? 
    private int tradedQuantity;
    private LocalDateTime executedTime;
    
    public TransactionHistory(String buyerOrderId, String sellerOrderId, String stockId, double buyPrice,
            double sellPrice, int tradedQuantity) {
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.stockId = stockId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.tradedQuantity = tradedQuantity;
        executedTime = LocalDateTime.now();
    }

    
}
