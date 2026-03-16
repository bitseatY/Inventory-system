package com.example.inventory_system.Dtos;

import com.example.inventory_system.Entities.StockChangeReason;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StockChangeRecord {
    @NotNull
    private long productId;
    @NotBlank
    private StockChangeReason reason;
    @NotNull
    private int quantity;
    @NotNull
    private long agentId;


}
