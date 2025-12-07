package fs.smartsupply.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fs.smartsupply.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
