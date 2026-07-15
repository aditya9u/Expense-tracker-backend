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
  private final CategoryRepository categoryRepository;
  private final CurrentUserService currentUserService;
  private final AnalyticsService analyticsService;

  public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {

    User user = currentUserService.getCurrentUser();

    Category category = categoryRepository.findById(expenseRequest.categoryId()).orElseThrow(()->new CategoryNotFoundException(expenseRequest.categoryId()));

     System.out.println(
            "Controller Thread : "
            + Thread.currentThread().getName()
        );

    Expense expense = new Expense();

    expense.setAmount(expenseRequest.amount());
    expense.setCategory(category);
    expense.setDate(expenseRequest.date());
    expense.setUser(user);
    expense.setDescription(expenseRequest.description());

    Expense expenseSaved = expenseRepository.save(expense);

    analyticsService.updateAnalytics(user.getId());

    return mapToResponse(expenseSaved);
    
    
  }

  public ExpenseResponse getExpenseById(Long id) {
    User user = currentUserService.getCurrentUser();

    return mapToResponse(expenseRepository.findByUserAndId(user,id).orElseThrow(()-> new ExpenseNotFoundException(id)));
    
  }

  public ExpenseResponse updateExpense(Long id, ExpenseRequest expenseRequest) {

    User user = currentUserService.getCurrentUser();

    Expense expense = expenseRepository.findByUserAndId(user,id).orElseThrow(()-> new ExpenseNotFoundException(id));

    Category category = categoryRepository.findById(expenseRequest.categoryId()).orElseThrow(()->new CategoryNotFoundException(expenseRequest.categoryId()));


    expense.setAmount(expenseRequest.amount());
    expense.setDescription(expenseRequest.description());
    expense.setDate(expenseRequest.date());
    expense.setUser(user);
    expense.setCategory(category);

    return mapToResponse(expense);
    
  }

  public void deleteExpense(Long id) {

    User user = currentUserService.getCurrentUser();

    Expense expense = expenseRepository.findByUserAndId(user,id).orElseThrow(()-> new ExpenseNotFoundException(id));

    expenseRepository.delete(expense);
    
  }

 public Page<ExpenseResponse> getExpenses(
        Long categoryId,
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable){

          User user = currentUserService.getCurrentUser();

          if(categoryId!=null){
            return expenseRepository.findByUserAndCategoryId(user,categoryId,pageable).map(this::mapToResponse);

          }
          if(startDate!=null && endDate!=null){
            return expenseRepository.findByUserAndDateBetween(user,startDate, endDate,pageable).map(this::mapToResponse);

          }
          if(startDate!=null && endDate==null){
            return expenseRepository.findByUserAndDateGreaterThanEqual(user,startDate,pageable).map(this::mapToResponse);

          }
          if(startDate==null && endDate!=null){
            return expenseRepository.findByUserAndDateLessThanEqual(user,endDate,pageable).map(this::mapToResponse);

          }

          return expenseRepository.findByUser(user,pageable).map(this::mapToResponse);

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
