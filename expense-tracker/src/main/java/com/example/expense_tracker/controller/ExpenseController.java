package com.example.expense_tracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  // @GetMapping("/all")
  // public List<ExpenseResponse> getAllExpenses(){

  //   return expenseService.getAllExpenses();
  // }

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

  @GetMapping
public Page<ExpenseResponse> getExpenses(
        @RequestParam(required = false) Long categoryId,
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String direction ) {

         

        Pageable pageAble = PageRequest.of(page, size);

    return  expenseService.getExpenses(
            categoryId,
            startDate,
            endDate,
            pageAble);
}
}