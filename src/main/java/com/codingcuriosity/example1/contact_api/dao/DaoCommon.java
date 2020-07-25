package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.mapper.AddressRowMapper;
import com.codingcuriosity.example1.contact_api.mapper.CommunicationRowMapper;
import com.codingcuriosity.example1.contact_api.mapper.IdentificationRowMapper;
import com.codingcuriosity.example1.contact_api.query.ReadAddressInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.ReadBasicInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.ReadCommInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;
import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Common routines; exclusive for Dao classes
 */
public class DaoCommon {

  private DaoCommon() {}

  static List<Identification> findAllIdentification(NamedParameterJdbcTemplate template) {
    String sql = "";
    SqlStatement stRead = new ReadBasicInfoQueryStatement();
    try {
      stRead.build();
      sql = stRead.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new IdentificationRowMapper());
  }

  static List<Address> findAllAddress(NamedParameterJdbcTemplate template, String contactid) {
    String sql = "";
    SqlStatement stRead = new ReadAddressInfoQueryStatement(contactid);
    try {
      stRead.build();
      sql = stRead.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new AddressRowMapper());
  }

  static List<Communication> findAllCommunication(NamedParameterJdbcTemplate template,
      String contactid) {
    String sql = "";
    SqlStatement stRead = new ReadCommInfoQueryStatement(contactid);
    try {
      stRead.build();
      sql = stRead.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new CommunicationRowMapper());
  }

  static String buildSql(SqlStatement statement) {
    String sql = "";
    try {
      statement.build();
      sql = statement.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    return sql;
  }

}
