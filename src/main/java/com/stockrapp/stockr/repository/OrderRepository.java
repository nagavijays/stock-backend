package com.stockrapp.stockr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockrapp.stockr.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
