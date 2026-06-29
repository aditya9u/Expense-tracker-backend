package com.example.expense_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.requests.ExpenseRequest;
import com.example.expense_tracker.dto.responses.ExpenseResponse;
import com.example.expense_tracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

  private final ExpenseService expenseService;

  @PostMapping
  public ExpenseResponse addExpense(@RequestBody @Valid ExpenseRequest expenseRequest){

    return expenseService.addExpense(expenseRequest);

  }
  @GetMapping
  public List<ExpenseResponse> getAllExpenses(){

    return expenseService.getAllExpenses();
  }

  @GetMapping("/{id}")
  public ExpenseResponse getExpenseById(@PathVariable Long id){
    return expenseService.getExpenseById(id);
  }

  @PutMapping("/{id}")
  public ExpenseResponse updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseRequest expenseRequest){
    return expenseService.updateExpense(id,expenseRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteExpense(@PathVariable Long id){
     expenseService.deleteExpense(id);
  }
    
}