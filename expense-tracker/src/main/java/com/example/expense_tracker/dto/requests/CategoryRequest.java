package com.example.expense_tracker.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
  @NotBlank
  String name,
  String disctiption,
  String color,
  String icon
) {
  
}
