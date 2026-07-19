package com.example.expense_tracker.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.example.expense_tracker.dto.external.ExchangeRateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExchangeRateClient {

    private final RestClient restClient;

    public ExchangeRateResponse getRates(String baseCurrency) {

    return restClient.get()
            .uri(
                "https://api.exchangerate.fun/latest?base="
                + baseCurrency
            )
            .retrieve()
            .body(ExchangeRateResponse.class);
}

}