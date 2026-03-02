package com.example.inventory_system.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_change_records")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Product product;
    @Column(nullable = false)
    private StockChangeReason reason;
    @Column(nullable = false)
    private LocalDateTime actionTime;
    @Column(nullable = false)
    private BigInteger quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private User agent;


}