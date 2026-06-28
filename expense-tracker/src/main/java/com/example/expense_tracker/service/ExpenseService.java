package com.example.expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.requests.ExpenseRequest;
import com.example.expense_tracker.dto.responses.ExpenseResponse;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.exception.UserNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {

    User user = userRepository.findById(expenseRequest.userId()).orElseThrow(()->new UserNotFoundException(expenseRequest.userId()));
    Category category = categoryRepository.findById(expenseRequest.categoryId()).orElseThrow(()->new CategoryNotFoundException(expenseRequest.categoryId()));

    Expense expense = new Expense();

    expense.setAmount(expenseRequest.amount());
    expense.setCategory(category);
    expense.setDate(expenseRequest.date());
    expense.setUser(user);
    expense.setDescription(expenseRequest.description());

    Expense expenseSaved = expenseRepository.save(expense);

    return new ExpenseResponse(expenseSaved.getId(), expenseSaved.getDescription(), expenseSaved.getAmount(), expenseSaved.getDate(), expenseSaved.getUser().getId(), expenseSaved.getUser().getName(), expenseSaved.getCategory().getId(), expenseSaved.getCategory().getName());
    
    
  }

  // public List<ExpenseResponse> getAllExpenses() {


    
  // }

  // public ExpenseResponse getExpenseById(Long id) {
    
  // }

  // public ExpenseResponse updateExpense(Long id, ExpenseRequest expenseRequest) {
    
  // }

  // public ExpenseResponse deleteExpense(Long id) {
    
  // }


  
}
