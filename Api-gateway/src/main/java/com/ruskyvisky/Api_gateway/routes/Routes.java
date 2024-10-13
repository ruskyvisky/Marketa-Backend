package com.ruskyvisky.api_gateway.routes;


import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> ProductServiceRoute() {
        return GatewayRouterFunctions.route("product-service").
                route(RequestPredicates.path("/api/product/**"),
                        HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product-service-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> ProductSwaggerRoute(){
        return GatewayRouterFunctions.route("product_swagger_route")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api/docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product-swagger-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderServiceRoute() {
        return GatewayRouterFunctions.route("order-service").
                route(RequestPredicates.path("/api/order/**"),
                        HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order-service-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderSwaggerRoute(){
        return GatewayRouterFunctions.route("order_swagger_route")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api/docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order-swagger-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> InventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory-service").
                route(RequestPredicates.path("/api/inventory/**"),
                        HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory-service-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> InventorySwaggerRoute(){
        return GatewayRouterFunctions.route("inventory_swagger_route")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api/docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory-swagger-circuit-breaker"
                        ,URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute(){
        return route("fallbackRoute")
                .GET("/fallback",request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service is not available. Try again later")).build();
    }


}
