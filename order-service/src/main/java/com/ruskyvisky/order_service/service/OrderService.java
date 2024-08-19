package com.ruskyvisky.order_service.service;

import com.ruskyvisky.order_service.client.InventoryClient;
import com.ruskyvisky.order_service.dto.OrderRequest;
import com.ruskyvisky.order_service.entity.Order;
import com.ruskyvisky.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;


    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient){
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(OrderRequest orderRequest){
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isInStock){
            Order order =new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);

        }
        else {
            throw new RuntimeException("Product with skuCode "+orderRequest.skuCode()+" is out of stock");
        }


    }
}
