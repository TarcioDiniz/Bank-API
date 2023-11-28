package com.api.bank;

import com.api.bank.configuration.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        LogConfig.setLogFile("BankApplication");

        SpringApplication.run(BankApplication.class, args);

        Logger logger = LogManager.getLogger(BankApplication.class);
        logger.info("Iniciando Aplicação");

    }

}
