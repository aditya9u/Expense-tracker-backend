package com.example.expense_tracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.responses.*;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.util.AnalyticsPeriod;
import org.springframework.scheduling.annotation.Async;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

  private final CurrentUserService currentUserService;
  private final ExpenseRepository expenseRepository;

  public List<MonthlySummaryResponse> monthlySummary() {
    
    User currentUser = currentUserService.getCurrentUser();

    return expenseRepository.getMonthlySummary(currentUser);
    
  }

  public List<CategorySpendingResponse> categorySummary(AnalyticsPeriod period) {

    User user = currentUserService.getCurrentUser();

    LocalDate starDate = calculateStartDate(period);
    LocalDate enDate = LocalDate.now();

    return expenseRepository.getCategorySpending(user, starDate, enDate);
   
  }

        @Async
        public void updateAnalytics(Long userId) {

        System.out.println(
            "Analytics running on thread: "
            + Thread.currentThread().getName()
        );

         try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
            "Analytics Updated For User "
            + userId
        );
    }

 private LocalDate calculateStartDate(
        AnalyticsPeriod period) {

    return switch (period) {

        case DAY ->
                LocalDate.now();

        case MONTH ->
                LocalDate.now().withDayOfMonth(1);

        case YEAR ->
                LocalDate.now().withDayOfYear(1);
    };
}

  public CurrentMonthlySummaryResponse currentMonthSummary() {

    User user = currentUserService.getCurrentUser();

    LocalDate startDate =
            LocalDate.now().withDayOfMonth(1);

    LocalDate endDate =
            LocalDate.now();

    BigDecimal totalExpense =
            expenseRepository.getTotalExpense(
                    user,
                    startDate,
                    endDate
            );

    Long totalTransactions =
            expenseRepository.getTransactionCount(
                    user,
                    startDate,
                    endDate
            );

    List<CategorySpendingResponse>
            categorySummary =
            expenseRepository.getCategorySpending(
                    user,
                    startDate,
                    endDate
            );

    CategorySpendingResponse topCategory =
            categorySummary.isEmpty()
                    ? null
                    : categorySummary.get(0);
    
    return new CurrentMonthlySummaryResponse(
      YearMonth.now().toString(),
      totalExpense,
            totalTransactions,
            topCategory != null
                    ? topCategory.category()
                    : null,
            topCategory != null
                    ? topCategory.amount()
                    : BigDecimal.ZERO,
            categorySummary
    );
 
    
  }

  
}
