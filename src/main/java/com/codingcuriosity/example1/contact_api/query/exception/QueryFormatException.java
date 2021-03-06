package com.codingcuriosity.example1.contact_api.query.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QueryFormatException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = -1602941540913862180L;

  public QueryFormatException(String errMsg) {
    super(errMsg);
  }
}
