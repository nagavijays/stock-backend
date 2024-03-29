package com.stockrapp.stockr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockrapp.stockr.entity.Order;
import com.stockrapp.stockr.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    StockMatchingService stockMatchingService;

    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order) {
        order.setStatus("OPEN");
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }


}
