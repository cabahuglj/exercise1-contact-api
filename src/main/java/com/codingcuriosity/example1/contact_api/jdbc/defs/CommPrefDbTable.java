package com.codingcuriosity.example1.contact_api.jdbc.defs;

public enum CommPrefDbTable implements DbTable {
  INSTANCE;

  private static final String name = "commpref";

  public enum CommPrefColumn implements DbColumn {
    COMM_ID("commid"), //
    CONTACT_ID("contactid");

    private final String colName;

    private CommPrefColumn(String colName) {
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
    CommPrefColumn column = (CommPrefColumn) e;
    return column.getName();
  }
}
