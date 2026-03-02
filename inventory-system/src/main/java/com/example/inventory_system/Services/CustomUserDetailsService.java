package com.example.inventory_system.Services;

import com.example.inventory_system.Entities.User;
import com.example.inventory_system.Repositories.UserRepo;
import com.example.inventory_system.Security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found."));
        return new CustomUserDetails(user);
    }
}
