package com.stockrapp.stockr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockrapp.stockr.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByStatusAndOrderType(String status, String orderType);
    
    List<Order> findByUserId(String userId);


}
