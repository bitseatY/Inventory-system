package com.example.inventory_system.Repositories;

import com.example.inventory_system.Entities.StockChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockChangeRepo extends JpaRepository<StockChange,Long> {
}
