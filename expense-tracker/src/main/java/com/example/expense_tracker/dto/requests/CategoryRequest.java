package com.example.expense_tracker.dto.requests;

public record CategoryRequest(
  String name,
  String disctiption,
  String color,
  String icon
) {
  
}
