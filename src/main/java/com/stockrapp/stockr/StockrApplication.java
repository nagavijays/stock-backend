package com.stockrapp.stockr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockrApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockrApplication.class, args);
	}
}
