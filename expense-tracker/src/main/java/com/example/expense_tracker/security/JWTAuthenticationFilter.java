package com.example.expense_tracker.security;

import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;




@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{

  private final JWTService jwtService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");

    if(authHeader==null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);
    String username = jwtService.extractUsername(jwt);

    if (username != null
        && SecurityContextHolder.getContext()
                .getAuthentication() == null) {

                  User user = userRepository
        .findByEmail(username)
        .orElse(null);

        if (user != null
        && jwtService.isTokenValid(jwt, user)) {

          UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
                user,
                null,
                Collections.emptyList()
        );

        SecurityContextHolder.getContext()
        .setAuthentication(authToken);


        filterChain.doFilter(request, response);

        System.out.println("JWT FILTER EXECUTED");
        System.out.println("TOKEN = " + jwt);
        System.out.println("USERNAME = " + username);
  
      }
  }
      }
  
 }
