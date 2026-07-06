package com.example.expense_tracker.dto.responses;

import java.math.BigDecimal;

public record CategorySpendingResponse(
  String category,
  BigDecimal amount
) {
  
}