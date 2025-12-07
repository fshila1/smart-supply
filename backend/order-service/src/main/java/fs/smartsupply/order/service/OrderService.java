package fs.smartsupply.order.service;

import org.springframework.stereotype.Service;

import fs.smartsupply.order.entity.Order;
import fs.smartsupply.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order create(Order order) {
        return repository.save(order);
    }

    public Order update(Long id, Order order) {
        order.setId(id);
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
