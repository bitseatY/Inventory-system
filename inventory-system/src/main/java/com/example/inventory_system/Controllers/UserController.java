package com.example.inventory_system.Controllers;


import com.example.inventory_system.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public void  register(@RequestParam String name,@RequestParam String password){
        userService.register(name,password);
    }

}
