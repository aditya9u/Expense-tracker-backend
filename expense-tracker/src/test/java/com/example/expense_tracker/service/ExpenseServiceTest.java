package com.example.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.expense_tracker.dto.requests.ExpenseRequest;
import com.example.expense_tracker.dto.responses.ExpenseResponse;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.exception.ExpenseNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.ExpenseRepository;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

  @Mock
  private  ExpenseRepository expenseRepository;
  @Mock
  private  CategoryRepository categoryRepository;
  @Mock
  private  CurrentUserService currentUserService;

  @InjectMocks
  private ExpenseService expenseService;

    @Test
    void shouldCreateExpenseSuccessfully(){

    User user = new User();
    user.setId(1L);

    Category category = new Category();
    category.setId(1L);

    ExpenseRequest request = new ExpenseRequest("Lunch", LocalDate.now(), 1L, new BigDecimal(500));

    when(currentUserService.getCurrentUser()).thenReturn(user);

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    Expense savedExpense = new Expense();

    savedExpense.setUser(user);
    savedExpense.setCategory(category);

    when(expenseRepository.save(any()))
            .thenReturn(savedExpense);


    ExpenseResponse response =
            expenseService.addExpense(request);

        assertNotNull(response);

  }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {

      User user = new User();
    user.setId(1L);

    ExpenseRequest request = new ExpenseRequest("Lunch", LocalDate.now(), 1L, new BigDecimal(500));

    when(currentUserService.getCurrentUser()).thenReturn(user);

    when(categoryRepository.findById(anyLong()))
        .thenReturn(Optional.empty());

    Expense savedExpense = new Expense();

    savedExpense.setUser(user);

      assertThrows(
        CategoryNotFoundException.class,
        () -> expenseService.addExpense(request)
      );

    }
   
    @Test
    void shouldGetExpenseByCurrentUserId(){
      User user = new User();
      user.setId(1L);

      Category category = new Category();
      category.setId(1L);

      when(currentUserService.getCurrentUser()).thenReturn(user);

      @Nullable Optional<Expense> expense = Optional.ofNullable(new Expense());
      Expense expense2 = expense.get();
      expense2.setCategory(category);
      expense2.setUser(user);
      expense2.setId(1L);
    

      when(expenseRepository.findByUserAndId(user,1L)).thenReturn(expense);

      ExpenseResponse expenseResponse = expenseService.getExpenseById(1L);

      assertNotNull(expenseResponse);


    }

    @Test
    void shouldThrowExceptionWhenExpenseNotFound(){
        User user = new User();
        user.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);

         assertThrows(
        ExpenseNotFoundException.class,
        () -> expenseService.getExpenseById(1L)
      );

    }
  
    @Test
    void shouldThrowExceptionWhenExpenseNotFoundWhenUpdating(){
        User user = new User();
        user.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);

        ExpenseRequest request = new ExpenseRequest("Lunch", LocalDate.now(), 1L, new BigDecimal(500));


        assertThrows(
        ExpenseNotFoundException.class,
        () -> expenseService.updateExpense(1L,request)
      );
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundWhenUpdating(){
        User user = new User();
        user.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);

        ExpenseRequest request = new ExpenseRequest("Lunch", LocalDate.now(), 1L, new BigDecimal(500));

         @Nullable Optional<Expense> expense = Optional.ofNullable(new Expense());
          

        when(expenseRepository.findByUserAndId(user,1L)).thenReturn(expense);


        assertThrows(
        CategoryNotFoundException.class,
        () -> expenseService.updateExpense(1L,request)
      );
    }

    @Test
    void shouldUpdateExpenseSuccessfully(){

        User user = new User();
        user.setId(1L);

        @Nullable Optional<Category> category = Optional.ofNullable(new Category());
        Category category2 = category.get();
        category2.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);

        ExpenseRequest request = new ExpenseRequest("Lunch", LocalDate.now(), 1L, new BigDecimal(500));

         @Nullable Optional<Expense> expense = Optional.ofNullable(new Expense());
         Expense expense2 = expense.get();
         expense2.setCategory(category2);
         expense2.setUser(user);
         expense2.setId(1L);

        when(expenseRepository.findByUserAndId(user,1L)).thenReturn(expense);
        when(categoryRepository.findById(1L)).thenReturn(category);

         ExpenseResponse expenseResponse = expenseService.updateExpense(1L,request);

        assertNotNull(expenseResponse);

    }

    @Test
    void expenseNotFoundWhileDeleting(){
        User user = new User();
        user.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);

        assertThrows(
            ExpenseNotFoundException.class,
            () -> expenseService.deleteExpense(1L)
        );

    }

    @Test
    void expenseDeletedSuccessfully(){
        User user = new User();
        user.setId(1L);

        when(currentUserService.getCurrentUser()).thenReturn(user);


         @Nullable Optional<Expense> expense = Optional.ofNullable(new Expense());
         Expense expense2 = expense.get();
         expense2.setUser(user);
         expense2.setId(1L);

        when(expenseRepository.findByUserAndId(user, 1L)).thenReturn(expense);

        expenseService.deleteExpense(1L);

         verify(expenseRepository).delete(expense2);

    }

}
