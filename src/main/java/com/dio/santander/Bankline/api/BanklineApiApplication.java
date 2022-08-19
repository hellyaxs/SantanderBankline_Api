package com.dio.santander.Bankline.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanklineApiApplication {
    @Value("${myApp.myProperty}")
	public static String variavel;
	public static void main(String[] args) {
		System.out.println(variavel);
		SpringApplication.run(BanklineApiApplication.class, args);
	}


}
