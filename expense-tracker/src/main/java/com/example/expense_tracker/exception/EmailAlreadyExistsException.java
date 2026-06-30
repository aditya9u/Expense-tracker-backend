package com.example.expense_tracker.exception;

/**
 * EmailAlreadyExistsException
 */
public class EmailAlreadyExistsException extends RuntimeException {

  public EmailAlreadyExistsException(String email){
    super("Email Already Exists : "+email);
  }
}
