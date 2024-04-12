package com.stockrapp.stockr.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger log = LoggerFactory.getLogger(StockMatchingService.class);

  private void runMatchingEngine() {
    boolean currentExecution = true;
    boolean previousExecution = false;

    while (true) {
      try {
        currentExecution = previousExecution;
        if(currentExecution)
          currentExecution = evaluateTrades();
          evaluateTrades():

        Thread.sleep(2000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private boolean evaluateTrades() {

    List<Order> buyOrders = orderRepository.findByStatusAndOrderType("OPEN", "BUY");
    List<Order> sellOrders = orderRepository.findByStatusAndOrderType("OPEN", "SELL");

    for (Order buyOrder : buyOrders) {
      for (Order sellOrder : sellOrders) {
        if (buyOrder.getStockId().equals(sellOrder.getStockId()) &&
            buyOrder.getPrice() >= sellOrder.getPrice()) {

          log.info("Found matching order for {}, qty {}", buyOrder.getStockId(), buyOrder.getQuantity());
          int tradedQuantity = saveExecutedTransaction(buyOrder, sellOrder);

          // Reduce the quantity for the matched orders for buy and sell.
          // An order can have multiple transactions (i.e trades)
          adjustOrderStatusAndQuantity(buyOrder, sellOrder, tradedQuantity);
        }
      }
    }
    return true;

  }

  private void adjustOrderStatusAndQuantity(Order buyOrder, Order sellOrder, int tradedQuantity) {
    buyOrder.setQuantity(buyOrder.getQuantity() - tradedQuantity);
    sellOrder.setQuantity(sellOrder.getQuantity() - tradedQuantity);
    if (buyOrder.getQuantity() == 0)
      buyOrder.setStatus("CLOSED");
    if (sellOrder.getQuantity() == 0)
      sellOrder.setStatus("CLOSED");
    orderRepository.save(buyOrder);
    orderRepository.save(sellOrder);
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
}