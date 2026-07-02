package com.example.expense_tracker.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.requests.LoginRequest;
import com.example.expense_tracker.dto.requests.RegisterRequest;
import com.example.expense_tracker.dto.responses.LoginResponse;
import com.example.expense_tracker.dto.responses.RegisterResponse;
import com.example.expense_tracker.dto.responses.UserResponse;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.service.CurrentUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final JWTService jwtService;
  private final CurrentUserService currentUserService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest){
      return authService.register(registerRequest);
  }

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){

    return authService.login(loginRequest);
  }

  @GetMapping("/profile")
  public UserResponse testToken(
        @RequestParam String token) {

          User user = currentUserService.getCurrentUser();

    return new UserResponse(user.getId(), user.getName(), user.getEmail());
}
  
}
