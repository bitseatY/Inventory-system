package com.example.inventory_system.Security;

import com.example.inventory_system.Services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;

    private String extractToken(HttpServletRequest httpRequest){
             String header= httpRequest.getHeader("Authorization");
             if(header!=null&&header.startsWith("bearer")){
                 return header.substring(7);
             }
             return  null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token=extractToken(request);
        if(token!=null&& jwtUtil.validateToken(token)){
            String username= jwtUtil.extractUsername(token);
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,
                                                               userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request,response);

    }
}
