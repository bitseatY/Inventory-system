package com.example.inventory_system.Services;

import com.example.inventory_system.Security.JwtUtil;
import com.example.inventory_system.Security.SecurityConfig;
import com.example.inventory_system.Entities.Role;
import com.example.inventory_system.Entities.User;
import com.example.inventory_system.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AuthService {
    private UserRepo userRepo;
    private SecurityConfig securityConfig;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;


    public void register(String name,String password){
         User user=new User();
         user.setUsername(name);
         String securePassword=securityConfig.passwordEncoder().encode(password);
         user.setPassword(securePassword);
         user.setRole(Role.STAFF);
         userRepo.save(user);
    }

    public String login(String username,String password){
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return jwtUtil.generateToken(userDetailsService.loadUserByUsername(username));

    }

}
