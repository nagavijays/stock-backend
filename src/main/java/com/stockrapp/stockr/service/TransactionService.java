package com.stockrapp.stockr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockrapp.stockr.entity.TransactionHistory;
import com.stockrapp.stockr.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionHistory save(TransactionHistory transaction) {
        return transactionRepository.save(transaction);
    }

    public List<TransactionHistory> getAll() {
        return transactionRepository.findAll();
    }

    public List<TransactionHistory> getByOrderId(String orderId, String orderType) {
        System.out.println("OrderType;:: " + orderType);
        List<TransactionHistory> trasactions = transactionRepository.findByBuyerOrderId(orderId);

        return ("BUY".equals(orderType)) ? transactionRepository.findByBuyerOrderId(orderId)
                : transactionRepository.findBySellerOrderId(orderId);
    }

}
