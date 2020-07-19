package com.codingcuriosity.example1.contact_api;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.codingcuriosity.example1.contact_api.jdbc.JdbcSqlConnect;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.data.AddressDbData;
import com.codingcuriosity.example1.contact_api.jdbc.data.AddressDbDataBuilder;
import com.codingcuriosity.example1.contact_api.query.EditAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.QueryResult;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class SmokeTestUpdate {
  public static void main(String[] args) {
    JdbcSqlConnect sqlConnect = JdbcSqlConnect.of("postgresql", "192.168.1.18", 5432,
        "contact-info-db", "postgres", "postgres1234");
    System.out.println(sqlConnect.getDbUrl());
    System.out.println(sqlConnect.getDbUsername());
    System.out.println(sqlConnect.getDbPassword());

    AddressDbDataBuilder addressBldr = new AddressDbDataBuilder();
    addressBldr.setAddressId("07d25d6f-0dda-4d9f-a0a4-7efb1ef706be");
    addressBldr.setType("work");
    addressBldr.setNumber(5678);
    addressBldr.setStreet("Yehey");
    addressBldr.setUnit("5dqww");
    addressBldr.setCity("Fuck-city");
    addressBldr.setState("AL");
    addressBldr.setZipCode("54321");
    addressBldr.setContactId("a68ba7cc-1ac9-4447-aef6-ae7f1ae46a50");
    AddressDbData address = addressBldr.build();
    EditAddressInfoUpdateStatement statement = new EditAddressInfoUpdateStatement(address);
    statement.setAddressNumber(6969);
    statement.setAddressUnit("Huh?!?");
    statement.setAddressCity("Mandaue");
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
