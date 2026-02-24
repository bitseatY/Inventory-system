package Entities;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example: "Color", "Size", "Weight"
    @Column(nullable = false)
    private String name;

    // Example: "Red", "XL", "1.5kg"
    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}