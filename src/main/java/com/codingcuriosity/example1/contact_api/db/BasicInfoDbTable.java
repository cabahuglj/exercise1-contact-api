package com.codingcuriosity.example1.contact_api.db;

public enum BasicInfoDbTable implements DbTable {
  INSTANCE;

  private static final String NAME = "basicinfo";

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

  public enum GenderEnum {
    MALE("M"), //
    FEMALE("F"), //
    OTHER("O");

    private final String val;

    private GenderEnum(String val) {
      this.val = val;
    }

    public String getVal() {
      return this.val;
    }
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getColumnName(Enum<? extends DbColumn> e) {
    BasicInfoColumn column = (BasicInfoColumn) e;
    return column.getName();
  }
}
