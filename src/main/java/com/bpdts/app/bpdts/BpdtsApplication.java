package com.bpdts.app.bpdts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BpdtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpdtsApplication.class, args);
	}

}
