package com.ruskyvisky.order_service.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface InventoryClient {


    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);


    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable){
        log.info("Fallback method called for skuCode: {} and quantity: {}", skuCode, quantity);
        return false;
    }
}
