package com.codingcuriosity.example1.contact_api.jdbc;

public interface JdbcSqlConnect {
  public static JdbcSqlConnect of(String db, String hostname, int port, String dbName,
      String username, String password) {
    SqlUtil.checkConnectionPrm(hostname, port, dbName, username, password);
    // NOTE: Only postgreSQL is supported for now.
    return new JdbcPostgreSqlConnect(hostname, port, dbName, username, password);
  }

  public abstract String getDbUrl();

  public abstract String getDbUsername();

  public abstract String getDbPassword();

}
