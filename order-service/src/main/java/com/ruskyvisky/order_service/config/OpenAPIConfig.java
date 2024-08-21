package com.ruskyvisky.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI OrderServiceAPI(){
        return new OpenAPI().info(new Info()
                .title("Order Service API")
                .description("Order Service API for Ruskyvisky")
                .version("v1.0.0"));
    }
}