package com.lms.loyalty_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.lms.loyalty_service.feign")
public class LoyaltyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyServiceApplication.class, args);
	}

}
