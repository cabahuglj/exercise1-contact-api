package com.codingcuriosity.example1.contact_api.query;

import java.sql.ResultSet;

public class QueryResult {
  private final boolean isSuccessful;
  private final ResultSet resultSet;
  private String errMsg = "";

  public QueryResult(ResultSet resultSet) {
    this.errMsg = "";
    this.isSuccessful = ((null == resultSet) ? false : true);
    this.resultSet = resultSet;
  }

  public QueryResult(int result) {
    this.resultSet = null;
    if (result > 0) {
      this.errMsg = "";
      this.isSuccessful = true;
    } else {
      this.errMsg = String.format("SQL Update failed. result = %d", result);
      this.isSuccessful = false;
    }
  }

  public QueryResult(boolean result, String resultMsg) {
    this.isSuccessful = result;
    this.resultSet = null;
    this.errMsg = ((null == resultMsg) ? "" : resultMsg);
  }

  public QueryResult(Exception exception) {
    this.isSuccessful = false;
    this.resultSet = null;
    if (null != exception) {
      this.errMsg = String.format("(%s) %s", exception.getClass(), exception.getMessage());
    } else {
      this.errMsg = "Fatal: Unknown Exception occurred.";
    }
  }

  public boolean isSuccessful() {
    return this.isSuccessful;
  }

  public ResultSet getResultSet() {
    return this.resultSet;
  }

  public String getErrorMsg() {
    return this.errMsg;
  }
}
