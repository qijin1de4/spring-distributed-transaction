package io.hqj.spring.dtx.order.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.hqj.spring.dtx.order.domain.product.Product;
import io.hqj.spring.dtx.order.repository.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static Random random = new Random();


    private static Cache<Object, Object> caffeine = Caffeine.newBuilder()
            .maximumSize(1000L)
            .initialCapacity(100)
            .expireAfterWrite(600, TimeUnit.SECONDS)
            .recordStats()
            .build();

    public static Boolean isexist(String key){
        Object cacheValue = caffeine.getIfPresent(key);
        if (cacheValue!=null){
            return true;
        }else {
            return false;
        }
    }

    public static <T> T setValue(Supplier<T> supplier, String key){
        T t = supplier.get();
        if (null != key && null != t) {
            caffeine.put(key, t);
        }
        return t;
    }

    public static <T> T getValue(String key){
        Object cacheValue = caffeine.getIfPresent(key);
        if (cacheValue!=null){
            return (T)cacheValue;
        }else {
            return null;
        }
    }

    public Product createProduct(Product product) {
        if(product.getPrice() <= 0) {
            throw new RuntimeException("价格必须大于0！");
        }
        if(product.getQuantity() < 0) {
            throw new RuntimeException("产品数量必须大于或等于0！");
        }
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long id) {
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productRepository.findById(id);
    }

    public Optional<Product> findProductByIdFallBack(Long id) {
        log.info(" Product id: {}, CircuitBreaker open, fall back !", id);
        if(!isexist(id.toString())) {
            setValue(() -> productRepository.findById(id), id.toString());
        }
        return getValue(id.toString());
    }

    public Product updateProduct(Product product) {
        if(product.getQuantity() < 0) {
            throw new RuntimeException("产品数量必须大于或等于0！");
        }
        return productRepository.save(product);
    }

}
