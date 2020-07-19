package com.codingcuriosity.example1.contact_api;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.codingcuriosity.example1.contact_api.jdbc.JdbcSqlConnect;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.query.DeleteCommPrefUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.QueryResult;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class SmokeTestDelete {

  public static void main(String[] args) {
    JdbcSqlConnect sqlConnect = JdbcSqlConnect.of("postgresql", "192.168.1.18", 5432,
        "contact-info-db", "postgres", "postgres1234");
    System.out.println(sqlConnect.getDbUrl());
    System.out.println(sqlConnect.getDbUsername());
    System.out.println(sqlConnect.getDbPassword());

    DeleteCommPrefUpdateStatement statement = new DeleteCommPrefUpdateStatement();
    statement.setContactId("3338c1c3-2b95-4c66-8912-27af4a78a620");
    try {
      statement.build();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    System.out.println(statement.getSqlStatement());
    QueryResult result = SqlUtil.executeUpdate(sqlConnect, statement);
    System.out.println("Query Result");
    System.out.println("Is Successful? " + result.isSuccessful());
    System.out.println("Error : " + result.getErrorMsg());
    System.out.println(result.getResultSet());
    ResultSet res = result.getResultSet();
    if (null != res) {
      try {
        res.next();
        System.out.println(res.getString(1));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }
}
