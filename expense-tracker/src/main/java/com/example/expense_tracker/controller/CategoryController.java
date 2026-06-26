package com.example.expense_tracker.controller;


import com.example.expense_tracker.entity.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public Category createCategory(@RequestBody Category category){

    return categoryService.save(category);

  }

  @GetMapping
  public List<Category> getAllCategories(){

    return categoryService.findAll();

  }

  @GetMapping("/{id}")
  public Optional<Category> getCategory(@PathVariable Long id){
    return categoryService.findById(id);
  }

  @PutMapping("/{id}")
  public Category updateCategory(@PathVariable Long id, @RequestBody Category category){

    return categoryService.update(id, category);

  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id){
    categoryService.delete(id);
  }
  
}
