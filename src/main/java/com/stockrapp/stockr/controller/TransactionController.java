package com.stockrapp.stockr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockrapp.stockr.entity.TransactionHistory;
import com.stockrapp.stockr.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionHistory> create(@RequestBody TransactionHistory transaction) {
        try {
            TransactionHistory saved = transactionService.save(transaction);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<TransactionHistory>> getAll() {
        try {
            List<TransactionHistory> transactions = transactionService.getAll();

            if (transactions.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/{orderId}/{orderType}")
    public ResponseEntity<List<TransactionHistory>> getByOrderId(@PathVariable String orderId, @PathVariable String orderType) {
        try {
            List<TransactionHistory> transactionsByOrder = transactionService.getByOrderId(orderId, orderType);

            // if (transactionsByOrder.isEmpty())
            //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(transactionsByOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
