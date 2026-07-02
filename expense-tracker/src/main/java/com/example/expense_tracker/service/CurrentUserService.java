package com.example.expense_tracker.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.expense_tracker.entity.User;

@Service
public class CurrentUserService {



  public User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        return (User) authentication.getPrincipal();
    }
  
}
