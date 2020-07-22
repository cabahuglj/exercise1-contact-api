package com.codingcuriosity.example1.contact_api.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import com.codingcuriosity.example1.contact_api.mapper.CommIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.mapper.CommunicationRowMapper;
import com.codingcuriosity.example1.contact_api.mapper.ContactIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddCommPrefQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommunicationInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteCommPrefUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteCommunicationInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.ReadCommunicationInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateCommunicationQueryStatement;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;
import com.codingcuriosity.example1.contact_api.validation.CommonCheck;

@Repository
public class CommunicationDaoImpl implements CommunicationDao {

  @Autowired
  NamedParameterJdbcTemplate template;

  public CommunicationDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  private String insertCommEntryOnTable(SqlStatement stCreate) {
    String sql = "";
    SqlStatement stAdd = stCreate;
    try {
      stAdd.build();
      sql = stAdd.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return sql;
  }

  // Common Function for deleting an entry in the table
  private void deleteCommEntryOnTable(SqlStatement stDel) {
    String sql = "";
    SqlStatement stDelete = stDel;
    try {
      stDelete.build();
      sql = stDelete.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    template.update(sql, new HashMap<String, Object>());
  }

  @Override
  public List<Communication> findAll(String contactid) {
    String sql = "";
    SqlStatement stRead = new ReadCommunicationInfoQueryStatement(contactid);
    try {
      stRead.build();
      sql = stRead.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new CommunicationRowMapper());
  }

  @Override
  public CommId insertCommunication(String contactid, Communication dat) {
    // Add new CommunicationInfo entry to DB
    String sql = insertCommEntryOnTable(new AddCommunicationInfoQueryStatement(contactid, dat));
    CommId result = template.query(sql, new CommIdResultSetExtractor());

    if (CommonCheck.isValidUuid(result.getCommId()) && dat.isPreferred()) {
      // Also add to CommPref DB if set to preferred
      sql = insertCommEntryOnTable(new AddCommPrefQueryStatement(contactid, result.getCommId()));
      template.query(sql, new CommIdResultSetExtractor());
    }

    return result;
  }

  @Override
  public void updateCommunication(String commid, Communication dat) {
    String sql = "";
    SqlStatement stUpdate = new UpdateCommunicationQueryStatement(commid, dat);
    try {
      stUpdate.build();
      sql = stUpdate.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    ContactId result = template.query(sql, new ContactIdResultSetExtractor());
    if (CommonCheck.isValidUuid(result.getContactId())) {
      // Delete CommPref Info from DB (will also act as "clean")
      deleteCommEntryOnTable(new DeleteCommPrefUpdateStatement(commid));

      if (dat.isPreferred()) {
        // Add CommPref in case it is set to preferred.
        sql = insertCommEntryOnTable(new AddCommPrefQueryStatement(dat.getContactId(), commid));
        template.query(sql, new CommIdResultSetExtractor());
      }
    }
  }

  @Override
  public void deleteCommunication(String commid) {
    // Delete Communication Info from DB
    deleteCommEntryOnTable(new DeleteCommunicationInfoUpdateStatement(commid));

    // Delete CommPref Info from DB
    deleteCommEntryOnTable(new DeleteCommPrefUpdateStatement(commid));
  }
}
