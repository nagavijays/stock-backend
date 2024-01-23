package com.stockrapp.stockr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stockrapp.stockr.entity.Order;
import com.stockrapp.stockr.entity.TransactionHistory;
import com.stockrapp.stockr.repository.TransactionRepository;

public class StockMatchingService {

  @Autowired
  TransactionRepository transactionRepository;

  //Not an ideal datastructure.. For time being ok. 
  // Maybe it could be a event queue..  or cache or from db. 
  private List<Order> buyOrders;
  private List<Order> sellOrders;
  private List<TransactionHistory> transactions;

  public StockMatchingService() {
    buyOrders = new ArrayList<>();
    sellOrders = new ArrayList<>();
    transactions = new ArrayList<>();
  }

  /**
   * Start executing matching service on first buy or sell order. 
   */
  public void addBuyOrder(Order buyOrder) {
    buyOrders.add(buyOrder);
    evaluateTrades();
  }

  public void addSellOrder(Order sellOrder) {
    sellOrders.add(sellOrder);
    evaluateTrades();
  }


  private void evaluateTrades() {
    for (Order buyOrder : buyOrders) {
      for (Order sellOrder : sellOrders) {
        if (buyOrder.getStockId().equals(sellOrder.getStockId()) &&
            buyOrder.getPrice() >= sellOrder.getPrice()) {
          int tradedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

          TransactionHistory aTransaction = new TransactionHistory(buyOrder.getId(), sellOrder.getId(),
               buyOrder.getStockId(), buyOrder.getPrice(), sellOrder.getPrice(), tradedQuantity);

          transactionRepository.save(aTransaction);

          //Reduce the quantity for the matched orders for buy and sell. 
          //An order can have multiple transactions (i.e trades)     
          buyOrder.setQuantity(buyOrder.getQuantity() - tradedQuantity);
          sellOrder.setQuantity(sellOrder.getQuantity() - tradedQuantity);
          
          if (buyOrder.getQuantity() == 0)
            buyOrders.remove(buyOrder);
          if (sellOrder.getQuantity() == 0)
            sellOrders.remove(sellOrder);

          evaluateTrades(); // Call till shutdown.. Wise idea??
          return;
        }
      }
    }






  }
  

  public List<TransactionHistory> getTransactions() {
    return transactions;
  }
}