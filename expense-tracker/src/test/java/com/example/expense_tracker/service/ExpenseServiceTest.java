package com.example.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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
  
}
