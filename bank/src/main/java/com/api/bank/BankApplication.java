package com.api.bank;

import com.api.bank.configuration.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        LogConfig.setLogFile("BankApplication");
        SpringApplication.run(BankApplication.class, args);
    }
}