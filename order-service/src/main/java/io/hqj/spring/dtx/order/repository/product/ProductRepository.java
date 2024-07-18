package io.hqj.spring.dtx.order.repository.product;

import io.hqj.spring.dtx.order.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
