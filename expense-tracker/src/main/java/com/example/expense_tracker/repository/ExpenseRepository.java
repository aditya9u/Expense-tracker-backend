package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findExpenseByUserId(Long userId);

  List<Expense> findExpenseByCategoryId(Long categoryId);

  List<Expense> findByUserIdAndCategoryId(
        Long userId,
        Long categoryId);

  List<Expense> findByDateBetween(
        LocalDate startDate,
        LocalDate endDate);

   List<Expense> findByDateLessThanEqual(
        LocalDate endDate);
        
  List<Expense> findByDateGreaterThanEqual(LocalDate startDate);

  
}
