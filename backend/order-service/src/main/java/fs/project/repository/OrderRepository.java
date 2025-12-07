package fs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fs.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
