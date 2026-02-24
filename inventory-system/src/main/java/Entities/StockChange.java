package Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_changes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the product (or ProductVariation if you have variations)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Amount of change (positive = added, negative = removed)
    @Column(nullable = false)
    private Integer quantityChange;

    // Reason for stock change
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockChangeReason reason;

    // Optional: who did it
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User performedBy;

    // Timestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}