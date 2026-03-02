package com.example.inventory_system.Services;

import com.example.inventory_system.Security.SecurityConfig;
import com.example.inventory_system.Entities.Role;
import com.example.inventory_system.Entities.User;
import com.example.inventory_system.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;
    private SecurityConfig securityConfig;


    public void register(String name,String password){
         User user=new User();
         user.setUsername(name);
         String securePassword=securityConfig.passwordEncoder().encode(password);
         user.setPassword(securePassword);
         user.setRole(Role.STAFF);
         userRepo.save(user);
    }

}
