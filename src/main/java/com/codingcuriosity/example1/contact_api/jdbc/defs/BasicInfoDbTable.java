package com.codingcuriosity.example1.contact_api.jdbc.defs;

public enum BasicInfoDbTable implements DbTable {
  INSTANCE;

  private static final String name = "basicinfo";

  public enum BasicInfoColumn implements DbColumn {
    CONTACT_ID("contactid"), //
    FIRST_NAME("firstname"), //
    LAST_NAME("lastname"), //
    BIRTH_DATE("birthdate"), //
    GENDER("gender"), //
    TITLE("title");

    private final String colName;

    private BasicInfoColumn(String colName) {
      this.colName = colName;
    }

    @Override
    public String getName() {
      return colName;
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getColumnName(Enum<? extends DbColumn> e) {
    BasicInfoColumn column = (BasicInfoColumn) e;
    return column.getName();
  }
}
