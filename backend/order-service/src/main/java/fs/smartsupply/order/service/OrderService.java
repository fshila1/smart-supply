package fs.smartsupply.order.service;

import fs.smartsupply.order.DTO.OrderRequest;
import fs.smartsupply.order.DTO.OrderResponse;
import fs.smartsupply.order.DTO.OrderStatus;
import fs.smartsupply.order.entity.Order;
import fs.smartsupply.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest dto) {
        System.out.println(dto.getStatus());
        try {
            Order order = Order.builder()
                .userId(dto.getUserId())
                .status(dto.getStatus())
                .totalAmount(dto.getTotalAmount())
                .currency(dto.getCurrency())
                .build();

        order = orderRepository.save(order);
        return toResponseDTO(order);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        
    }

    public OrderResponse getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::toResponseDTO)
                .orElse(null); // You can throw custom NotFoundException if needed
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        order = orderRepository.save(order);

        return toResponseDTO(order);
    }

    private OrderResponse toResponseDTO(Order order) {
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .currency(order.getCurrency())
                .correlationId(order.getCorrelationId())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}