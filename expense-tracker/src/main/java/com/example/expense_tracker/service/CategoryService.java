package com.example.expense_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

private final CategoryRepository categoryRepository;

public Category save(Category category){
  return categoryRepository.save(category);
}

public List<Category> findAll(){
  return categoryRepository.findAll();
}

public Optional<Category> findById(Long id){
  return categoryRepository.findById(id);
}

public Category update(Long id,Category categoryRequest){

  Category category = (Category) categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));

  category.setName(categoryRequest.getName());
  category.setDescription(categoryRequest.getDescription());
  category.setColor(categoryRequest.getColor());
  category.setIcon(categoryRequest.getIcon());

  return categoryRepository.save(category);
}

  public void delete(Long id){
    categoryRepository.deleteById(id);
  }
  
}
