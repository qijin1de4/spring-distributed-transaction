package io.hqj.spring.dtx.order.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.hqj.spring.dtx.common.domain.BusinessResponse;
import io.hqj.spring.dtx.order.domain.product.Product;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private io.hqj.spring.dtx.order.service.ProductService productService;

    private static final CircuitBreakerRegistry circuitBreakerRegistry =  CircuitBreakerRegistry.of(
            CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
            .slidingWindowSize(60)
            .minimumNumberOfCalls(1000)
            .slowCallDurationThreshold(Duration.ofSeconds(1))
            .slowCallRateThreshold(80)
            .waitDurationInOpenState(Duration.ofSeconds(10))
            .permittedNumberOfCallsInHalfOpenState(10)
            .build());

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

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("getProduct");
        Supplier<ResponseEntity<BusinessResponse<Product>>> supplier = CircuitBreaker.decorateSupplier(cb, () -> productService.findProductById(id).map(product -> ResponseEntity.ok(BusinessResponse.getResponse(product)) ).orElseGet(() -> ResponseEntity.badRequest().build()));
        return Try.ofSupplier(supplier).recover(e ->
            productService.findProductByIdFallBack(id).map(product -> ResponseEntity.ok(BusinessResponse.getResponse(product)) ).orElseGet(() -> ResponseEntity.badRequest().build())
        ).get();
    }
}
