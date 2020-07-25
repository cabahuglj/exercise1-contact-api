package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import com.codingcuriosity.example1.contact_api.mapper.CommIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.mapper.CommunicationRowMapper;
import com.codingcuriosity.example1.contact_api.mapper.ContactIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddCommPrefQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteCommInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteCommPrefUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteGrpCommInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.ReadCommInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateCommInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;
import com.codingcuriosity.example1.contact_api.validation.CommonCheck;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommunicationDaoImpl implements CommunicationDao {

  @Autowired
  NamedParameterJdbcTemplate template;

  public CommunicationDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Communication> findAll(String contactid) {
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

  @Override
  public CommId insertCommunication(String contactid, Communication dat) {
    // Add new CommunicationInfo entry to DB
    SqlStatement stAdd = new AddCommInfoQueryStatement(contactid, dat);
    String sql = DaoCommon.buildSql(stAdd);
    CommId result = template.query(sql, new CommIdResultSetExtractor());

    if (null != result && CommonCheck.isValidUuid(result.getCommId()) && dat.isPreferred()) {
      // Also add to CommPref DB if set to preferred
      stAdd = new AddCommPrefQueryStatement(contactid, result.getCommId());
      sql = DaoCommon.buildSql(stAdd);
      template.query(sql, new CommIdResultSetExtractor());
    }

    return result;
  }

  @Override
  public void updateCommunication(String commid, Communication dat) {
    SqlStatement stUpdate = new UpdateCommInfoQueryStatement(commid, dat);
    String sql = DaoCommon.buildSql(stUpdate);
    ContactId result = template.query(sql, new ContactIdResultSetExtractor());

    if (null != result && CommonCheck.isValidUuid(result.getContactId())) {
      // Delete CommPref Info from DB (will also act as "clean")
      SqlStatement stDelete = new DeleteCommPrefUpdateStatement(commid);
      sql = DaoCommon.buildSql(stDelete);
      template.update(sql, new HashMap<>());

      if (dat.isPreferred()) {
        // Add CommPref in case it is set to preferred.
        SqlStatement stAdd = new AddCommPrefQueryStatement(dat.getContactId(), commid);
        sql = DaoCommon.buildSql(stAdd);
        template.query(sql, new CommIdResultSetExtractor());
      }
    }
  }

  @Override
  public void deleteCommunication(String commid) {
    // Delete Communication Info from DB
    SqlStatement stDelete = new DeleteCommInfoUpdateStatement(commid);
    String sql = DaoCommon.buildSql(stDelete);
    template.update(sql, new HashMap<>());

    // Delete CommPref Info from DB
    stDelete = new DeleteCommPrefUpdateStatement(commid);
    sql = DaoCommon.buildSql(stDelete);
    template.update(sql, new HashMap<>());
  }

  @Override
  public void deleteAllCommunication(String contactid) {
    // Delete All Communication Info from DB for the specified contact
    SqlStatement stDeleteGrp = new DeleteGrpCommInfoUpdateStatement(contactid);
    String sql = DaoCommon.buildSql(stDeleteGrp);
    template.update(sql, new HashMap<>());
  }
}
