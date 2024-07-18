package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.order.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private io.hqj.spring.dtx.order.service.ProductService productService;


    @PostMapping("/products")
    public Product createProduct(@RequestBody Product Product) {
        return productService.createProduct(Product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.findProductById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
