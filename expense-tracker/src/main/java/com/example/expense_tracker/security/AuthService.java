package com.example.expense_tracker.security;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.requests.LoginRequest;
import com.example.expense_tracker.dto.requests.RegisterRequest;
import com.example.expense_tracker.dto.responses.LoginResponse;
import com.example.expense_tracker.dto.responses.RegisterResponse;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.exception.EmailAlreadyExistsException;
import com.example.expense_tracker.exception.InvalidCredentialsException;
import com.example.expense_tracker.exception.UserNotFoundException;
import com.example.expense_tracker.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JWTService jwtService;

  public RegisterResponse register(RegisterRequest registerRequest) {

    if (userRepository.existsByEmail(registerRequest.email())) {
    throw new EmailAlreadyExistsException(registerRequest.email());
  }

    User user = new User();

    user.setName(registerRequest.name());
    user.setEmail(registerRequest.email());
    user.setPassword(passwordEncoder.encode(registerRequest.password()));

    User savedUser = userRepository.save(user);

    return new RegisterResponse(
        savedUser.getId(),
        savedUser.getName(),
        savedUser.getEmail(),
        "User registered successfully"
);

    
  }

  public LoginResponse login(LoginRequest loginRequest) {
   User user = userRepository.findByEmail(loginRequest.email()).orElseThrow(()->new InvalidCredentialsException(loginRequest.email()));

   boolean matches = passwordEncoder.matches(loginRequest.password(),user.getPassword());


    if (!matches) {
        throw new InvalidCredentialsException(
                "Invalid email or password"
        );
    }

    String token = jwtService.generateToken(user);

      return new LoginResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            token,
            "Bearer"
    );

  }
  
}
