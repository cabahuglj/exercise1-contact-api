package com.codingcuriosity.example1.contact_api;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.codingcuriosity.example1.contact_api.jdbc.JdbcSqlConnect;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.data.AddressDbDataBuilder;
import com.codingcuriosity.example1.contact_api.query.AddAddressInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddBasicInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommPrefQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommunicationInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.QueryResult;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class SmokeTest {

  public static void main(String[] args) throws SQLException {
    JdbcSqlConnect sqlConnect = JdbcSqlConnect.of("postgresql", "192.168.1.18", 5432,
        "contact-info-db", "postgres", "postgres1234");
    System.out.println(sqlConnect.getDbUrl());
    System.out.println(sqlConnect.getDbUsername());
    System.out.println(sqlConnect.getDbPassword());

    AddBasicInfoQueryStatement statement = new AddBasicInfoQueryStatement();
    statement.setFirstName("Leil Jan");
    statement.setLastName("Cabahug");
    statement.setBirthDate("1990-05-18");
    statement.setGender("Male");
    statement.setTitle("Senior Engineer");
    statement.build();
    System.out.println(statement.getSqlStatement());
    QueryResult result = SqlUtil.executeQuery(sqlConnect, statement);
    System.out.println("Query Result");
    System.out.println("Is Successful? " + result.isSuccessful());
    System.out.println("Error : " + result.getErrorMsg());
    System.out.println(result.getResultSet());
    ResultSet res = result.getResultSet();
    String contactid = "";
    if (null != res) {
      res.next();
      contactid = res.getString(1);
      System.out.println(contactid);
    }

    AddressDbDataBuilder addrBuilder = new AddressDbDataBuilder();
    addrBuilder.setType("home");
    addrBuilder.setNumber(5678);
    addrBuilder.setStreet("Fuckk");
    addrBuilder.setUnit("5dqww");
    addrBuilder.setCity("Fuck-city");
    addrBuilder.setState("WV");
    addrBuilder.setZipCode("12345");
    addrBuilder.setContactId("a68ba7cc-1ac9-4447-aef6-ae7f1ae46a50");
    AddAddressInfoQueryStatement statement2 = new AddAddressInfoQueryStatement(addrBuilder.build());
    try {
      statement2.build();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    System.out.println(statement2.getSqlStatement());
    result = SqlUtil.executeQuery(sqlConnect, statement2);
    System.out.println("Query Result");
    System.out.println("Is Successful? " + result.isSuccessful());
    System.out.println("Error : " + result.getErrorMsg());
    System.out.println(result.getResultSet());
    res = result.getResultSet();
    if (null != res) {
      res.next();
      System.out.println(res.getString(1));
    }

    AddCommunicationInfoQueryStatement statement3 = new AddCommunicationInfoQueryStatement();
    statement3.setContactId("a68ba7cc-1ac9-4447-aef6-ae7f1ae46a50");
    statement3.setType("cell");
    statement3.setValue("hehehehe");
    try {
      statement3.build();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    System.out.println(statement3.getSqlStatement());
    result = SqlUtil.executeQuery(sqlConnect, statement3);
    System.out.println("Query Result");
    System.out.println("Is Successful? " + result.isSuccessful());
    System.out.println("Error : " + result.getErrorMsg());
    System.out.println(result.getResultSet());
    res = result.getResultSet();
    String commid = "";
    if (null != res) {
      res.next();
      commid = res.getString(1);
      System.out.println(commid);
    }

    AddCommPrefQueryStatement statement4 = new AddCommPrefQueryStatement();
    statement4.setCommId(commid);
    statement4.setContactId("3338c1c3-2b95-4c66-8912-27af4a78a620");
    try {
      statement4.build();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    System.out.println(statement4.getSqlStatement());
    result = SqlUtil.executeQuery(sqlConnect, statement4);
    System.out.println("Query Result");
    System.out.println("Is Successful? " + result.isSuccessful());
    System.out.println("Error : " + result.getErrorMsg());
    System.out.println(result.getResultSet());
    res = result.getResultSet();
    if (null != res) {
      res.next();
      System.out.println(res.getString(1));
    }
  }
}
