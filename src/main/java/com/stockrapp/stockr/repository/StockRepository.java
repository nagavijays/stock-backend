package com.stockrapp.stockr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockrapp.stockr.entity.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

}
