package com.stockrapp.stockr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "trades")
public class TradeTransaction {

    @Id
    private String transactionId;
    private String buyerOrderId;
    private String sellerOrderId;
    private String stockId;
    private double buyPrice; // Should use java.util.Currency? TODO:
    private double sellPrice; // Is it required to store transacted price twice? 
    private int tradedQuantity;
    
    public TradeTransaction(String buyerOrderId, String sellerOrderId, String stockId, double buyPrice,
            double sellPrice, int tradedQuantity) {
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.stockId = stockId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.tradedQuantity = tradedQuantity;
    }

    
}
