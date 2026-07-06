package com.example.expense_tracker.dto.responses;

import java.math.BigDecimal;
import java.util.List;

public record CurrentMonthlySummaryResponse(
        String month,

        BigDecimal totalExpense,

        Long totalTransactions,

        String highestSpendingCategory,

        BigDecimal highestSpendingAmount,

        List<CategorySpendingResponse> categorySummary
) {
  
}
