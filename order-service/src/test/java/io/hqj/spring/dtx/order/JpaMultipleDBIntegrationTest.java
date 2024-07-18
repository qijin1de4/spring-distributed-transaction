package io.hqj.spring.dtx.order;

import io.hqj.spring.dtx.order.domain.product.Product;
import io.hqj.spring.dtx.order.repository.account.AccountRepository;
import io.hqj.spring.dtx.order.repository.order.OrderRepository;
import io.hqj.spring.dtx.order.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableTransactionManagement
class JpaMultipleDBIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional(transactionManager = "productTransactionManager")
    void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setPrice(20);
        product.setName("T-bag");
        product.setQuantity(200);
        product.setCode(10001L);
        product = productRepository.save(product);
        assertNotNull(productRepository.findById(product.getId()));
    }

    @Test
    @Transactional(transactionManager = "productTransactionManager")
    void whenCreatingProductWithSameName_thenRollback() {
        Product product1 = new Product();
        product1.setPrice(10);
        product1.setName("bra");
        product1.setQuantity(500);
        product1.setCode(10002L);
        product1 = productRepository.save(product1);
        assertNotNull(productRepository.findById(product1.getId()));

        Product product2 = new Product();
        product2.setPrice(50);
        product2.setName("bra");
        product2.setQuantity(200);
        product2.setCode(10002L);

        try {
            productRepository.save(product2);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Failed");
            System.out.println(e.getMessage());
        }
        assertFalse(productRepository.findById(product2.getId()).isPresent());
    }

}
