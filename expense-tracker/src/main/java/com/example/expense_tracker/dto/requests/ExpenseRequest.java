package com.example.expense_tracker.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDate;


public record ExpenseRequest(
  String description,
  LocalDate date,
  Long userId,
  Long categoryId,
  BigDecimal amount
) {
  
}
