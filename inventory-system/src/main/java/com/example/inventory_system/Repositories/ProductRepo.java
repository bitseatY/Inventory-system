package com.example.inventory_system.Repositories;

import com.example.inventory_system.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategoryId(long categoryId);
}
