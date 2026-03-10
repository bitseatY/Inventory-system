package com.example.inventory_system.Dtos;

import com.example.inventory_system.Entities.StockChangeReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StockChangeRecord {
    private String product;
    private int quantity;
    private String agent;
    private StockChangeReason reason;

}
