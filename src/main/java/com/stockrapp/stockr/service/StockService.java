package com.stockrapp.stockr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockrapp.stockr.entity.Stock;
import com.stockrapp.stockr.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

}
