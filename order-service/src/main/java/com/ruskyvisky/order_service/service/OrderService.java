package com.ruskyvisky.order_service.service;

import com.ruskyvisky.order_service.client.InventoryClient;
import com.ruskyvisky.order_service.dto.OrderRequest;
import com.ruskyvisky.order_service.entity.Order;
import com.ruskyvisky.order_service.event.OrderPlacedEvent;
import com.ruskyvisky.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String , OrderPlacedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient , KafkaTemplate<String , OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
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

            // KAFKA SEND MESSAGE
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();

            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail("test@gmail.com");
            log.info("Order placed event sent for order number: {}", order.getOrderNumber());
            kafkaTemplate.send("order-placed", orderPlacedEvent);
        }
        else {
            throw new RuntimeException("Product with skuCode "+orderRequest.skuCode()+" is out of stock");
        }


    }
}
