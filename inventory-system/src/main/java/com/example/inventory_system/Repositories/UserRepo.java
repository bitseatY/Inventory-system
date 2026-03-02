package com.example.inventory_system.Repositories;

import com.example.inventory_system.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
         Optional<User> findByUsername(String username);
}
