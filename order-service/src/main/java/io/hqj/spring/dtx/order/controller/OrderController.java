package io.hqj.spring.dtx.order.controller;

import io.hqj.spring.dtx.common.domain.BusinessResponse;
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
    public ResponseEntity<BusinessResponse<Order>> createOrder(@RequestBody Order order) {
        try {
            return ResponseEntity.ok(BusinessResponse.getResponse(orderService.createOrder(order)));
        } catch(Exception e) {
            return  ResponseEntity.badRequest().body(BusinessResponse.getResponse(500, e.getMessage(), order));
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<BusinessResponse<Order>> getOrder(@PathVariable Long id) {
        return orderService.findOrderById(id).map(order -> ResponseEntity.ok(BusinessResponse.getResponse(order)) ).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
