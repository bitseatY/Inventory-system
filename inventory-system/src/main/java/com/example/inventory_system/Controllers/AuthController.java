package com.example.inventory_system.Controllers;

import com.example.inventory_system.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    public void  register(@RequestParam String username, @RequestParam String password){
        userService.register(username,password);
    }

}
