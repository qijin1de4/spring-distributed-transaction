package io.hqj.spring.dtx.order.service;

import io.hqj.spring.dtx.order.domain.product.Product;
import io.hqj.spring.dtx.order.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

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
        return productRepository.findById(id);
    }

    public Product updateProduct(Product product) {
        if(product.getQuantity() < 0) {
            throw new RuntimeException("产品数量必须大于或等于0！");
        }
        return productRepository.save(product);
    }

}
