package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;

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

  
}
