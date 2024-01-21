package com.stockrapp.stockr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.stockrapp.stockr.entity.Stock;
import com.stockrapp.stockr.entity.User;
import com.stockrapp.stockr.repository.OrderRepository;
import com.stockrapp.stockr.repository.StockRepository;
import com.stockrapp.stockr.repository.UserRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  StockRepository stockRepository;

  @Autowired
  OrderRepository orderRepository;

  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();

    userRepository.save(new User("jack"));
    userRepository.save(new User("john"));
    userRepository.save(new User("russell"));
    userRepository.save(new User("robin"));

    System.out.println("############## USERS ################");
    for (User user : userRepository.findAll()) {
      System.out.println(user);
    }

    stockRepository.deleteAll();

    stockRepository.save(new Stock("Infosys" , "INFY"));
    stockRepository.save(new Stock("Tata Consultancy" , "TCS"));
    stockRepository.save(new Stock("Reliance" , "RIL"));
    stockRepository.save(new Stock("Hdfc Bank" , "HDFCBAN"));
    
    System.out.println("############## STOCKS ################");
    for (Stock stock : stockRepository.findAll()) {
      System.out.println(stock);
    }

  }

}
