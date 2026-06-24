package com.example.expense_tracker.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expenses")
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String description;

  private Date date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private long amount;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  
  
  
}
