package com.ruskyvisky.inventory_service.service;


import com.ruskyvisky.inventory_service.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
     private final InventoryRepository inventoryRepository;

        public InventoryService(InventoryRepository inventoryRepository) {
            this.inventoryRepository = inventoryRepository;
        }

        public boolean isInStock(String skuCode,Integer quantity) {
            log.info(" Start -- Received request to check stock for skuCode {}, with quantity {}", skuCode, quantity);
            boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
            log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, isInStock);
            return isInStock;        }
}
