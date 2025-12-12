package fs.smartsupply.inventory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import fs.smartsupply.common.events.OrderCancelledEvent;
import fs.smartsupply.common.events.OrderCompletedEvent;
import fs.smartsupply.common.events.OrderCreatedEvent;
import fs.smartsupply.common.events.ProductStockDeductedEvent;
import fs.smartsupply.common.events.ProductStockReleasedEvent;
import fs.smartsupply.common.events.ProductStockReservationFailedEvent;
import fs.smartsupply.common.events.ProductStockReservedEvent;
import fs.smartsupply.inventory.entity.ProductStock;
import fs.smartsupply.inventory.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryWorkflowService {

    private final InventoryRepository inventoryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // ============================
    // 1️⃣ HANDLE ORDER CREATED
    // ============================
    public void handleOrderCreated(OrderCreatedEvent event) {

        log.info("Handling OrderCreatedEvent: {}", event);

        boolean allAvailable = event.getItems().stream().allMatch(item -> {
            ProductStock stock = inventoryRepository.findById(item.sku()).get();
            return stock != null && stock.getAvailableQty() >= item.qty();
        });

        if (allAvailable) {
            onReserveStock(event);
        } else {
            publishReservationFailed(event);
        }
    }

    public void onReserveStock(OrderCreatedEvent event) {

        // decrement stock
        event.getItems().forEach(item -> {
            ProductStock stock = inventoryRepository.findById(item.sku()).get();
            stock.setAvailableQty(stock.getAvailableQty() - item.qty());
            inventoryRepository.save(stock);
        });

        // publish SUCCESS event
        ProductStockReservedEvent reservedEvent =
                new ProductStockReservedEvent(event.getOrderId(), event.getItems());

        kafkaTemplate.send("inventory.reserved", reservedEvent);

        log.info("Published InventoryReservedEvent: {}", reservedEvent);
    }

    private void publishReservationFailed(OrderCreatedEvent event) {

        ProductStockReservationFailedEvent failedEvent =
                new ProductStockReservationFailedEvent(event.getOrderId(), "Insufficient Inventory");

        kafkaTemplate.send("inventory.reservation.failed", failedEvent);

        log.warn("Published InventoryReservationFailedEvent: {}", failedEvent);
    }


    // ============================
    // 2️⃣ HANDLE ORDER CANCELLED
    // ============================
    public void handleOrderCancelled(OrderCancelledEvent event) {

        log.info("Handling OrderCancelledEvent: {}", event);


        // restore stock
        event.getItems().forEach(item -> {
            ProductStock stock = inventoryRepository.findById(item.sku()).get();
            if (stock != null) {
                stock.setAvailableQty(stock.getAvailableQty() + item.qty());
                inventoryRepository.save(stock);
            }
        });

        ProductStockReleasedEvent releasedEvent =
                new ProductStockReleasedEvent(event.getOrderId(), event.getItems());

        kafkaTemplate.send("inventory.released", releasedEvent);

        log.info("Published InventoryReleasedEvent: {}", releasedEvent);
    }


    // ============================
    // 3️⃣ HANDLE ORDER COMPLETED
    // ============================
    public void handleOrderCompleted(OrderCompletedEvent event) {

        log.info("Handling OrderCompletedEvent: {}", event);

        // permanent stock deduction (if using reserved stock strategy)
        event.getItems().forEach(item -> {
            ProductStock stock = inventoryRepository.findById(item.sku()).get();
            if (stock != null) {
                stock.setReservedQty(stock.getReservedQty() - item.qty());
                inventoryRepository.save(stock);
            }
        });

        ProductStockDeductedEvent deductedEvent =
                new ProductStockDeductedEvent(event.getOrderId(), event.getItems());

        kafkaTemplate.send("inventory.deducted", deductedEvent);

        log.info("Published InventoryDeductedEvent: {}", deductedEvent);
    }
}
