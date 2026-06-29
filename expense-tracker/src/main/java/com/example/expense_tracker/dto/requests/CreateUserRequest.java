package com.example.expense_tracker.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
  @NotBlank
  String name,
  @Email
  String email,
  @NotBlank
  String password
) {
  
}
