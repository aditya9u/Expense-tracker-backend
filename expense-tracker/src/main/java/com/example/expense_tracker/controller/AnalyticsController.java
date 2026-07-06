package com.example.expense_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.responses.CategorySpendingResponse;
import com.example.expense_tracker.dto.responses.CurrentMonthlySummaryResponse;
import com.example.expense_tracker.dto.responses.MonthlySummaryResponse;
import com.example.expense_tracker.service.AnalyticsService;
import com.example.expense_tracker.util.AnalyticsPeriod;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

  private final AnalyticsService analyticsService;

  @GetMapping("/monthly-summary")
  public List<MonthlySummaryResponse> monthlySummary(){
   return analyticsService.monthlySummary();
  }

  @GetMapping("/category-summary")
  public List<CategorySpendingResponse> categorySummary(@RequestParam AnalyticsPeriod period){
   return analyticsService.categorySummary(period);
  }

  @GetMapping("/currentmonth-summary")
  public CurrentMonthlySummaryResponse currentMonthSummary(){
    return analyticsService.currentMonthSummary();
  }
  
}
