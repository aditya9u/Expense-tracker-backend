package com.example.expense_tracker.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.client.ExchangeRateClient;
import com.example.expense_tracker.dto.external.ExchangeRateResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final ExchangeRateClient exchangeRateClient;

  @GetMapping("/rates")
    public ExchangeRateResponse rates() {

        return exchangeRateClient.getRates("USD");
    }
  
}
