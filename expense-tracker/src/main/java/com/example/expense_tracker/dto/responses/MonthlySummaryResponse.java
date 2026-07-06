package com.example.expense_tracker.dto.responses;

import java.math.BigDecimal;

public record MonthlySummaryResponse(
        Integer year,
        Integer month,
        BigDecimal totalExpense,
        Long totalTransactions
) {
} ;
