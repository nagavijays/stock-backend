package com.stockrapp.stockr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockrapp.stockr.entity.Stock;
import com.stockrapp.stockr.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
    

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock) {
        System.out.println("Creae...");
        try {
            Stock saved = stockService.save(stock);
            System.out.println("Created...."+ saved.getCompanyName());
            System.out.println("Created...."+ saved.getId());
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        System.out.println("Something......");
        try {
            List<Stock> allStocks = stockService.getAll();

            if (allStocks.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(allStocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
