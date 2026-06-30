package com.example.expense_tracker.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.requests.ExpenseRequest;
import com.example.expense_tracker.dto.responses.ExpenseResponse;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.exception.ExpenseNotFoundException;
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

    return mapToResponse(expenseSaved);
    
    
  }

  public List<ExpenseResponse> getAllExpenses() {

    return expenseRepository.findAll().stream().map(this :: mapToResponse).toList();
  }

  public ExpenseResponse getExpenseById(Long id) {

    return mapToResponse(expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException(id)));
    
  }

  public ExpenseResponse updateExpense(Long id, ExpenseRequest expenseRequest) {

    Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException(id));

    User user = userRepository.findById(expenseRequest.userId()).orElseThrow(()->new UserNotFoundException(expenseRequest.userId()));
    Category category = categoryRepository.findById(expenseRequest.categoryId()).orElseThrow(()->new CategoryNotFoundException(expenseRequest.categoryId()));


    expense.setAmount(expenseRequest.amount());
    expense.setDescription(expenseRequest.description());
    expense.setDate(expenseRequest.date());
    expense.setUser(user);
    expense.setCategory(category);

    return mapToResponse(expense);
    
  }

  public void deleteExpense(Long id) {

    Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException(id));

    expenseRepository.delete(expense);
    
  }

 public Page<ExpenseResponse> getExpenses(
        Long userId,
        Long categoryId,
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable){

          if(userId!=null){
            return expenseRepository.findExpenseByUserId(userId,pageable).map(this::mapToResponse);
          }
          if(categoryId != null){
            return expenseRepository.findExpenseByCategoryId(categoryId,pageable).map(this::mapToResponse);

          }
          if(userId!=null && categoryId!=null){
            return expenseRepository.findByUserIdAndCategoryId(userId,categoryId,pageable).map(this::mapToResponse);

          }
          if(startDate!=null && endDate!=null){
            return expenseRepository.findByDateBetween(startDate, endDate,pageable).map(this::mapToResponse);

          }
          if(startDate!=null && endDate==null){
            return expenseRepository.findByDateGreaterThanEqual(startDate,pageable).map(this::mapToResponse);

          }
          if(startDate==null && endDate!=null){
            return expenseRepository.findByDateLessThanEqual(endDate,pageable).map(this::mapToResponse);

          }

          return expenseRepository.findAll(pageable).map(this::mapToResponse);

        }

   private ExpenseResponse mapToResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getUser().getId(),
                expense.getUser().getName(),
                expense.getCategory().getId(),
                expense.getCategory().getName()
        );
    }

    


  
}
