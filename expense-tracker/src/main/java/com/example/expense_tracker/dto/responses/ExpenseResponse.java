package com.example.expense_tracker.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate expenseDate,
        Long userId,
        String userName,
        Long categoryId,
        String categoryName
) {
  
}
