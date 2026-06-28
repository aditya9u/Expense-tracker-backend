package com.example.expense_tracker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Expenses")
@Getter
@Setter
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  
  
  
}
