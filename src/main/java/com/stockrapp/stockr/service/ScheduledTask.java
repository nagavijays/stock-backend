package com.stockrapp.stockr.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stockrapp.stockr.entity.TransactionHistory;
import com.stockrapp.stockr.repository.TransactionRepository;

@Component
public class ScheduledTask {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    // @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        List<TransactionHistory> allTrades = transactionRepository.findAll();
        System.out.println("#######################");
        for (TransactionHistory trade : allTrades) {
            System.out.println(trade);
        }
        System.out.println("--------------------------");
        
    }
    
}
