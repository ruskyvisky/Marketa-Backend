package com.ruskyvisky.order_service.config;


import com.ruskyvisky.order_service.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {


    @Value("${inventory.service.url}")
    private String inventoryServiceURL;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient = RestClient.builder().baseUrl(inventoryServiceURL).build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory =  HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);

    }
}
