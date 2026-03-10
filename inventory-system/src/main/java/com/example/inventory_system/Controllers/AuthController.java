package com.example.inventory_system.Controllers;

import com.example.inventory_system.Dtos.LoginRequest;
import com.example.inventory_system.Services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public void  register(@RequestParam String username, @RequestParam String password){
        authService.register(username,password);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
       return    authService.login(loginRequest.getUsername(),loginRequest.getPassword());
    }
}
