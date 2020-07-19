package com.codingcuriosity.example1.contact_api.jdbc.defs;

public enum CommunicationInfoDbTable implements DbTable {
  INSTANCE;

  private static final String name = "communicationinfo";

  public enum CommunicationInfoColumn implements DbColumn {
    COMM_ID("commid"), //
    TYPE("type"), //
    VALUE("value"), //
    CONTACT_ID("contactid");

    private final String colName;

    private CommunicationInfoColumn(String colName) {
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
    CommunicationInfoColumn column = (CommunicationInfoColumn) e;
    return column.getName();
  }
}
