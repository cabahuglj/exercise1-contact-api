package com.codingcuriosity.example1.contact_api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.ContactId;

public class ContactIdResultSetExtractor implements ResultSetExtractor<ContactId> {

  @Override
  public ContactId extractData(ResultSet rs) throws SQLException, DataAccessException {
    if (!rs.next()) {
      throw new SQLException("cannot perform add operation.");
    }

    String contactid = rs.getString(BasicInfoColumn.CONTACT_ID.getName());
    return new ContactId(contactid);
  }

}
