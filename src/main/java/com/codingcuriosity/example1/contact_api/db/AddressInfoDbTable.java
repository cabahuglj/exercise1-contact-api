package com.codingcuriosity.example1.contact_api.db;

public enum AddressInfoDbTable implements DbTable {
  INSTANCE;

  private static final String NAME = "addressinfo";

  public enum AddressInfoColumn implements DbColumn {
    ADDRESS_ID("addressid"), //
    ADDRESS("address"), //
    CONTACT_ID("contactid");

    private final String colName;

    private AddressInfoColumn(String colName) {
      this.colName = colName;
    }

    @Override
    public String getName() {
      return colName;
    }
  }

  public enum AddressCompColumn implements DbColumn {
    TYPE("type"), //
    NUMBER("number"), //
    STREET("street"), //
    UNIT("unit"), //
    CITY("city"), //
    STATE("state"), //
    ZIP_CODE("zipcode");

    private final String colName;

    private AddressCompColumn(String colName) {
      this.colName = colName;
    }

    @Override
    public String getName() {
      return colName;
    }
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getColumnName(Enum<? extends DbColumn> e) {
    AddressInfoColumn column = (AddressInfoColumn) e;
    return column.getName();
  }
}
