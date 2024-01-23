package com.stockrapp.stockr.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockrapp.stockr.entity.Order;
import com.stockrapp.stockr.entity.TransactionHistory;
import com.stockrapp.stockr.repository.OrderRepository;
import com.stockrapp.stockr.repository.TransactionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class StockMatchingService {

  @Autowired
  TransactionRepository transactionRepository;
  
  @Autowired
  OrderRepository orderRepository;

  private ExecutorService executorService;


  // Not an ideal datastructure.. For time being ok.
  // Maybe it could be a event queue.. or cache or from db.
  
  // private List<TransactionHistory> transactions;

  // public StockMatchingService() {
  //   buyOrders = new ArrayList<>();
  //   sellOrders = new ArrayList<>();
  //   transactions = new ArrayList<>();
  // }

  private void runMatchingEngine() {
    while (true) {
        try {
          evaluateTrades();
          Thread.sleep(2000);
          System.out.println("---------------");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    }
}

  private void evaluateTrades() {

    List<Order> buyOrders = orderRepository.findByStatusAndOrderType("OPEN", "BUY");
    List<Order> sellOrders = orderRepository.findByStatusAndOrderType("OPEN", "SELL");

    for (Order buyOrder : buyOrders) {
      for (Order sellOrder : sellOrders) {
        if (buyOrder.getStockId().equals(sellOrder.getStockId()) &&
            buyOrder.getPrice() >= sellOrder.getPrice()) {
          System.out.println("Found Match....");
          int tradedQuantity = saveExecutedTransaction(buyOrder, sellOrder);

          // Reduce the quantity for the matched orders for buy and sell.
          // An order can have multiple transactions (i.e trades)
          buyOrder.setQuantity(buyOrder.getQuantity() - tradedQuantity);
          sellOrder.setQuantity(sellOrder.getQuantity() - tradedQuantity);
          orderRepository.save(buyOrder);
          orderRepository.save(sellOrder);
        }
      }
    }
  }

  private int saveExecutedTransaction(Order buyOrder, Order sellOrder) {
    int tradedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

    TransactionHistory aTransaction = new TransactionHistory(buyOrder.getId(), sellOrder.getId(),
        buyOrder.getStockId(), buyOrder.getPrice(), sellOrder.getPrice(), tradedQuantity);

    transactionRepository.save(aTransaction);
    return tradedQuantity;
  }

  @PostConstruct
  public void startMatching() {
    executorService = Executors.newSingleThreadExecutor();
    executorService.execute(this::runMatchingEngine);
  }

  // public List<TransactionHistory> getTransactions() {
  //   return transactions;
  // }
}