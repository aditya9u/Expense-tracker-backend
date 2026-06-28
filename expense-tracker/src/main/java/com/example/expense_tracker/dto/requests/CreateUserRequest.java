package com.example.expense_tracker.dto.requests;

public record CreateUserRequest(
  String name,
  String email,
  String password
) {
  
}
