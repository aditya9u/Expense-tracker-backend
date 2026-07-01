package com.example.expense_tracker.exception;

/**
 * InvalidCredentialsException
 */
public class InvalidCredentialsException extends RuntimeException {

  public InvalidCredentialsException(String message){
      super("Invalid Email or Password : "+message);
  }

}
