package com.codingcuriosity.example1.contact_api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Identification;

public class IdentificationRowMapper implements RowMapper<Identification> {

  @Override
  public Identification mapRow(ResultSet rs, int rowNum) throws SQLException {
    String contactId = rs.getString(BasicInfoColumn.CONTACT_ID.getName());
    String firstName = rs.getString(BasicInfoColumn.FIRST_NAME.getName());
    String lastName = rs.getString(BasicInfoColumn.LAST_NAME.getName());
    String dob = rs.getString(BasicInfoColumn.BIRTH_DATE.getName());
    String gender = rs.getString(BasicInfoColumn.GENDER.getName());
    String title = rs.getString(BasicInfoColumn.TITLE.getName());
    return new Identification(contactId, firstName, lastName, dob, gender, title);
  }

}
