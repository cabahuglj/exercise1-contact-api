package com.codingcuriosity.example1.contact_api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.CommId;

public class CommIdResultSetExtractor implements ResultSetExtractor<CommId> {

  @Override
  public CommId extractData(ResultSet rs) throws SQLException, DataAccessException {
    if (!rs.next()) {
      throw new SQLException("cannot perform add operation.");
    }

    String commId = rs.getString(CommunicationInfoColumn.COMM_ID.getName());
    return new CommId(commId);
  }

}
