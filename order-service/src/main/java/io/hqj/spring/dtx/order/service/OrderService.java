package io.hqj.spring.dtx.order.service;

import io.hqj.spring.dtx.order.domain.account.Account;
import io.hqj.spring.dtx.order.domain.order.Order;
import io.hqj.spring.dtx.order.domain.product.Product;
import io.hqj.spring.dtx.order.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Transactional(transactionManager = "chainedTransactionManager")
    public Order createOrder(Order order) {
        Optional<Product> prodOptional = productService.findProductById(order.getProductId());
        Optional<Account> accountOptional = accountService.findAccountById(order.getCustomerId());
        if(!prodOptional.isPresent()) {
            throw new RuntimeException("产品"+order.getProductId()+"不存在！");
        }
        if(!accountOptional.isPresent()) {
            throw new RuntimeException("用户"+order.getCustomerId()+"不存在！");
        }
        Product product = prodOptional.get();
        Account account = accountOptional.get();

        product.setQuantity(product.getQuantity() - order.getQuantity());
        account.setBalance(account.getBalance() - (order.getQuantity() * product.getPrice()));

        productService.updateProduct(product);
        accountService.updateAccount(account);
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

}
