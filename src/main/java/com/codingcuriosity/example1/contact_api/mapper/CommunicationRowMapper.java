package com.codingcuriosity.example1.contact_api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommInfoTrnsColumn;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Communication;

public class CommunicationRowMapper implements RowMapper<Communication> {

  @Override
  public Communication mapRow(ResultSet rs, int rowNum) throws SQLException {
    String commid = rs.getString(CommunicationInfoColumn.COMM_ID.getName());
    String type = rs.getString(CommunicationInfoColumn.TYPE.getName());
    String value = rs.getString(CommunicationInfoColumn.VALUE.getName());
    String contactid = rs.getString(CommunicationInfoColumn.CONTACT_ID.getName());
    boolean isPreferred = rs.getBoolean(CommInfoTrnsColumn.PREFERRED.getName());
    return new Communication(commid, type, value, contactid, isPreferred);
  }

}
