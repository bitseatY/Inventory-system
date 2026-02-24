package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {           //https://github.com/bitseatY/Inventory-system.git

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Unique product code
    @Column(nullable = false, unique = true, length = 50)
    private String sku;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(length = 500)
    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Attribute> attributes;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costPrice;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private String quantityUnit;
    enum ProductStatus {ACTIVE,INACTIVE}
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    // Many products belong to one category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ProductStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
