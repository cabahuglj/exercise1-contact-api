package com.codingcuriosity.example1.contact_api.db;

public interface DbTable {

  public abstract String getName();

  public abstract String getColumnName(Enum<? extends DbColumn> e);
}
