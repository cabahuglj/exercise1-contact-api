package com.codingcuriosity.example1.contact_api.jdbc;

class JdbcPostgreSqlConnect implements JdbcSqlConnect {
  private final String url;
  private final String username;
  private final String password;

  JdbcPostgreSqlConnect(String hostname, int port, String dbName, String username,
      String password) {
    this.url = String.format("jdbc:postgresql://%s:%d/%s", hostname, port, dbName);
    this.username = username;
    this.password = password;
  }

  @Override
  public String getDbUrl() {
    return this.url;
  }

  @Override
  public String getDbUsername() {
    return this.username;
  }

  @Override
  public String getDbPassword() {
    return this.password;
  }
}
