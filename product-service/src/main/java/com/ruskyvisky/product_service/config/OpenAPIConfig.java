package com.ruskyvisky.product_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI(){
        return new OpenAPI().info(new Info()
                .title("Product Service API")
                .description("Product Service API for Ruskyvisky")
                .version("v1.0.0"));
    }
}
