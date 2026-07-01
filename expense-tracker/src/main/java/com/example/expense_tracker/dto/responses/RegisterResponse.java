package com.example.expense_tracker.dto.responses;

public record RegisterResponse(
        Long id,
        String name,
        String email,
        String message
) {
  
}
