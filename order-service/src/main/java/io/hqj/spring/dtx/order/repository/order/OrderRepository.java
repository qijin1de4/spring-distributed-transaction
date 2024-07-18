package io.hqj.spring.dtx.order.repository.order;

import io.hqj.spring.dtx.order.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
