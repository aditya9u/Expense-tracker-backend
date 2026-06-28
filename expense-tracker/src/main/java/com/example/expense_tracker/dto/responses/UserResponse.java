package com.example.expense_tracker.dto.responses;

public record UserResponse(
  Long id,
  String name,
  String email
) {
  
}
