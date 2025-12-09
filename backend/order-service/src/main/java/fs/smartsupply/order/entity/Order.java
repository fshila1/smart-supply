package fs.smartsupply.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import fs.smartsupply.order.DTO.OrderStatus;   // <-- FIXED PACKAGE

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "user_id", nullable = false, columnDefinition = "UUID")
    private UUID userId;

    // @Enumerated(EnumType.STRING)          // <-- REQUIRED
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status;
    

    @Column(name = "total_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "currency", length = 10, nullable = false)
    private String currency;

    @Column(name = "correlation_id", columnDefinition = "UUID")
    private UUID correlationId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();

        if (correlationId == null) {
            this.correlationId = UUID.randomUUID();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}