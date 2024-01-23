package com.stockrapp.stockr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stockrapp.stockr.entity.TransactionHistory;

public interface TransactionRepository extends MongoRepository<TransactionHistory, String> {

    
}
