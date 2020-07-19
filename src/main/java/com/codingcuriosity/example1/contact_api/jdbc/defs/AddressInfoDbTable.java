package com.codingcuriosity.example1.contact_api.jdbc.defs;

public enum AddressInfoDbTable implements DbTable {
  INSTANCE;

  private static final String name = "addressinfo";

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

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getColumnName(Enum<? extends DbColumn> e) {
    AddressInfoColumn column = (AddressInfoColumn) e;
    return column.getName();
  }
}
