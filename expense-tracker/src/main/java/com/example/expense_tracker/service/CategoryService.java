package com.example.expense_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.requests.CategoryRequest;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

private final CategoryRepository categoryRepository;

public Category save(CategoryRequest categoryRequest){

  Category category = new Category();

    category.setName(categoryRequest.name());
    category.setDescription(categoryRequest.disctiption());
    category.setColor(categoryRequest.color());
    category.setIcon(categoryRequest.icon());
  return categoryRepository.save(category);
}

public List<Category> findAll(){
  return categoryRepository.findAll();
}

public Optional<Category> findById(Long id){
  return Optional.of(categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id)));
}

public Category update(Long id,CategoryRequest categoryRequest){

  Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));

  category.setName(categoryRequest.name());
  category.setDescription(categoryRequest.disctiption());
  category.setColor(categoryRequest.color());
  category.setIcon(categoryRequest.icon());

  return categoryRepository.save(category);
}

  public void delete(Long id){

    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));

    categoryRepository.delete(category);
  }
  
}
