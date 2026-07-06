package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.dto.responses.CategorySpendingResponse;
import com.example.expense_tracker.dto.responses.MonthlySummaryResponse;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  Page<Expense> findExpenseByUserId(Long userId, Pageable pageable);

  Page<Expense> findExpenseByCategoryId(Long categoryId, Pageable pageable);

  Page<Expense> findByUserIdAndCategoryId(
        Long userId,
        Long categoryId, Pageable pageable);

  Page<Expense> findByDateBetween(
        LocalDate startDate,
        LocalDate endDate, Pageable pageable);

   Page<Expense> findByDateLessThanEqual(
        LocalDate endDate, Pageable pageable);
        
  Page<Expense> findByDateGreaterThanEqual(LocalDate startDate, Pageable pageable);
  Page<Expense> findByUser(
        User user,
        Pageable pageable
);

  Page<Expense> findByUserAndCategoryId(User user, Long categoryId, Pageable pageable);

  Page<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate, Pageable pageable);

  Page<Expense> findByUserAndDateGreaterThanEqual(User user, LocalDate startDate, Pageable pageable);

  Page<Expense> findByUserAndDateLessThanEqual(User user, LocalDate endDate, Pageable pageable);

  Optional<Expense> findByUserAndId(User user, Long id);

  @Query("""
  SELECT new com.example.expense_tracker.dto.responses
.CategorySpendingResponse(
        c.name,
        SUM(e.amount)
    )
  FROM Expense e 
  JOIN e.category c 
  WHERE e.user = :user
  AND e.date BETWEEN :startDate AND :endDate 
  GROUP BY c.name 
  ORDER BY SUM(e.amount) DESC  """)
  List<CategorySpendingResponse> getCategorySpending(@Param("user") User user, @Param("startDate")LocalDate starDate,
  @Param("endDate")LocalDate enDate);

  @Query("""
  SELECT 
  SUM(e.amount) 
  FROM Expense e
  WHERE e.user = :user
  AND e.date BETWEEN :startDate AND :endDate""")
  BigDecimal getTotalExpense(@Param("user")User user, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

  @Query("""
  SELECT 
  COUNT(e.id) 
  FROM Expense e
  WHERE e.user = :user
  AND e.date BETWEEN :startDate AND :endDate""")
  Long getTransactionCount(@Param("user")User user,@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
  @Query("""
      SELECT new com.example.expense_tracker.dto.responses
.MonthlySummaryResponse(
YEAR(e.date),
MONTH(e.date),
SUM(e.amount),
COUNT(e.id)
  )
FROM Expense e
WHERE e.user = :user
GROUP BY YEAR(e.date), MONTH(e.date)
ORDER BY YEAR(e.date), MONTH(e.date)
      """)
  List<MonthlySummaryResponse> getMonthlySummary(@Param("user")User currentUser);

  
}
