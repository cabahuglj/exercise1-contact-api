package com.codingcuriosity.example1.contact_api.mapper;

import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContactIdResultSetExtractor implements ResultSetExtractor<ContactId> {

  @Override
  public ContactId extractData(ResultSet rs) throws SQLException {
    if (!rs.next()) {
      throw new SQLException("cannot perform add operation.");
    }

    String contactid = rs.getString(BasicInfoColumn.CONTACT_ID.getName());
    return new ContactId(contactid);
  }

}
