package com.example.expense_tracker.service;

import org.springframework.stereotype.Service;
import com.example.expense_tracker.client.ExchangeRateClient;
import com.example.expense_tracker.dto.external.ExchangeRateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

      private final ExchangeRateClient client;


  public Double convertToINR(
        Double amount,
        String currency) {

    ExchangeRateResponse response =
            client.getRates(currency);

    Double rate =
            response.rates().get("INR");

    return amount * rate;
}
  
}
