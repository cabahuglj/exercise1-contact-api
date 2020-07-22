package com.codingcuriosity.example1.contact_api.query.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -7953435684839759048L;

  public EntityNotFoundException(String errMsg) {
    super(errMsg);
  }
}
