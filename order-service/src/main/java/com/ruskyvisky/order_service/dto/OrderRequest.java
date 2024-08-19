package com.ruskyvisky.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest( Long id,
         String orderNumber,
                            String skuCode,
         int quantity,
         BigDecimal price) {
}
