package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public interface SqlStatement {

  // Empty Value (Standard Text-based Type)
  public static final String EMPTYVAL = "\'\'";

  // Empty Value (Composite Data Type)
  public static final String EMPTYCOMPVAL = "\"\"";

  // Value Format for Standard Text-based Types
  public static final String VALFMT = "\'%s\'";

  // Value Format for Composite Data Types
  public static final String COMPVALFMT = "\"%s\"";

  // Value Format for Integer-based Data Types
  public static final String INTVALFMT = "%d";

  // Error Format for UUID related errors
  public final static String ERR_UUID_FMT = "%s provided is not a valid UUID. %s = %s";

  // Map format for SQL statements related to UPDATE
  public final static String MAP_EDIT_VAL = "%s = %s";

  public abstract void build() throws QueryFormatException;

  public abstract String getSqlStatement();
}
