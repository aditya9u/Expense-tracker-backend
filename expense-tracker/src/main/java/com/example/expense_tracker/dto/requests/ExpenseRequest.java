package com.example.expense_tracker.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ExpenseRequest(
  @NotBlank
  String description,
  @NotNull
  LocalDate date,
  @NotNull
  Long userId,
  @NotNull
  Long categoryId,
  @Positive
  BigDecimal amount
) {
  
}
