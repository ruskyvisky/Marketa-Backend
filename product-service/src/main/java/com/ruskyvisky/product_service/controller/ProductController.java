package com.ruskyvisky.product_service.controller;


import com.ruskyvisky.product_service.dto.ProductRequest;
import com.ruskyvisky.product_service.dto.ProductResponse;
import com.ruskyvisky.product_service.model.Product;
import com.ruskyvisky.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
      return    productService.getAllProducts();
    }

}
