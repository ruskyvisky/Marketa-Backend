package com.ruskyvisky.product_service.service;

import com.ruskyvisky.product_service.dto.ProductRequest;
import com.ruskyvisky.product_service.dto.ProductResponse;
import com.ruskyvisky.product_service.model.Product;
import com.ruskyvisky.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private  final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .stockQuantity(productRequest.stockQuantity())
                .build();
        productRepository.save(product);
        log.info("Product Created");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(),
                product.getStockQuantity()
        );    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription() , product.getPrice() ,product.getStockQuantity() ))
                .toList();
    }
}
