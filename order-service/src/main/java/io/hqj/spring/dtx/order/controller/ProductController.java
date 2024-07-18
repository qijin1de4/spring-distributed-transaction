package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.common.domain.BusinessResponse;
import io.hqj.spring.dtx.order.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private io.hqj.spring.dtx.order.service.ProductService productService;


    @PostMapping("/products")
    public ResponseEntity<BusinessResponse<Product>> createProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(BusinessResponse.getResponse(productService.createProduct(product)));
        } catch(Exception e) {
            return  ResponseEntity.badRequest().body(BusinessResponse.getResponse(500, e.getMessage(), product));
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<BusinessResponse<Product>> getProduct(@PathVariable Long id) {
        return productService.findProductById(id).map(product -> ResponseEntity.ok(BusinessResponse.getResponse(product)) ).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
