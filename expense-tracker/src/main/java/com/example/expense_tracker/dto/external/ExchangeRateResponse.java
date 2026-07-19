package com.example.expense_tracker.dto.external;

import java.util.Map;

public record ExchangeRateResponse(
        String base,
        Map<String, Double> rates
) {
}