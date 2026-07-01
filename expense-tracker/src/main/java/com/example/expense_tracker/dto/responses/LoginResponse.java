package com.example.expense_tracker.dto.responses;

public record LoginResponse(
        Long userId,
        String name,
        String email,
        String token,
        String tokenType
) {
  
}
