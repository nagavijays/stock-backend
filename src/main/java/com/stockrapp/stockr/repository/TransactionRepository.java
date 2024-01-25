package com.stockrapp.stockr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockrapp.stockr.entity.TransactionHistory;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionHistory, String> {

        List<TransactionHistory> findByBuyerOrderId(String buyerOrderid);

        List<TransactionHistory> findBySellerOrderId(String sellerOrderId);
    
}
