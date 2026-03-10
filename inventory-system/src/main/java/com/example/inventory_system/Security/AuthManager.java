package com.example.inventory_system.Security;

import jakarta.security.auth.message.config.AuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Configuration
public class AuthManager {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
           return config.getAuthenticationManager();
    }
}
