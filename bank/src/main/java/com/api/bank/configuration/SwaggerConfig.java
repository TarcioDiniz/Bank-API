package com.api.bank.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Bank-API").version("V1").description("It seeks to establish relationships between client and server for a banking system, ensuring safe and efficient crud.").termsOfService("").license(new License().name("MIT license").url("https://raw.githubusercontent.com/TarcioDiniz/Bank-API/main/LICENSE")));
    }

}
