// package com.example.expense_tracker.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.expense_tracker.dto.requests.CreateUserRequest;
// import com.example.expense_tracker.dto.responses.UserResponse;
// import com.example.expense_tracker.entity.User;
// import com.example.expense_tracker.service.UserService;

// import jakarta.validation.Valid;
// import jakarta.websocket.server.PathParam;
// import lombok.RequiredArgsConstructor;

// @RequestMapping("/api/users")
// @RestController
// @RequiredArgsConstructor
// public class UserController {

//   private final UserService userService;

//   @PostMapping
//   public UserResponse addUser(@RequestBody @Valid CreateUserRequest userRequest){
//     return userService.addUser(userRequest);   
//   }

//   @GetMapping
//   public List<User> getAllUsers(){
//     return userService.getAllUsers();
//   }

//   @GetMapping("/{id}")
//   public UserResponse getUserById(@PathVariable Long id){

//     return userService.getUserById(id);

//   }
//   @PutMapping("/{id}")
//   public UserResponse updateUser(@PathVariable Long id,@Valid @RequestBody CreateUserRequest userRequest){

//     return userService.updateUser(id,userRequest);

//   }

//   @DeleteMapping("/{id}")
//   public void deleteUser(@PathVariable Long id){

//      userService.deleteUser(id);

//   }


  
// }
