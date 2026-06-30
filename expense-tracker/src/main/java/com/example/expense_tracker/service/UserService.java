package com.example.expense_tracker.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.requests.CreateUserRequest;
import com.example.expense_tracker.dto.responses.UserResponse;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.exception.EmailAlreadyExistsException;
import com.example.expense_tracker.exception.UserNotFoundException;
import com.example.expense_tracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponse addUser(CreateUserRequest userRequest) {

    if(userRepository.existsByEmail(userRequest.email())){
      throw new EmailAlreadyExistsException(userRequest.email());
    }

    User user = new User();

    user.setEmail(userRequest.email());
    user.setName(userRequest.name());
    user.setPassword(userRequest.password());

    User savedUser = userRepository.save(user);

    return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserResponse getUserById(Long id) {
    
    User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));

    return new UserResponse(user.getId(), user.getName(), user.getEmail());

  }

  public UserResponse updateUser(Long id, CreateUserRequest userRequest) {
    
    User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

    user.setEmail(userRequest.email());
    user.setName(userRequest.name());
    user.setPassword(userRequest.password());

    User savedUser = userRepository.save(user);

    return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());


  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    userRepository.delete(user);

  }
  
}
