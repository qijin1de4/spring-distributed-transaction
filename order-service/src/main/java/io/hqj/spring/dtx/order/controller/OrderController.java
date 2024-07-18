package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.order.domain.order.Order;
import io.hqj.spring.dtx.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findOrderById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
