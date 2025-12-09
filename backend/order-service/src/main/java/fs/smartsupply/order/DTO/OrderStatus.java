package fs.smartsupply.order.DTO;

public enum OrderStatus {
    PENDING,        // Order created but not yet confirmed
    CONFIRMED,      // Payment verified or manual confirmation done
    PROCESSING,     // Warehouse / system is preparing the order
    SHIPPED,        // Order handed over to delivery service
    DELIVERED,      // Order delivered to the customer
    CANCELLED,      // Cancelled by customer or system
    FAILED,         // Payment or system failure
    RETURN_REQUESTED, // Customer requested a return
    RETURNED        // Product returned successfully
}