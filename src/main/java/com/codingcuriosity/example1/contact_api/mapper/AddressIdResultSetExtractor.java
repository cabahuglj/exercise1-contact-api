package com.codingcuriosity.example1.contact_api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.AddressId;

public class AddressIdResultSetExtractor implements ResultSetExtractor<AddressId> {

  @Override
  public AddressId extractData(ResultSet rs) throws SQLException, DataAccessException {
    if (!rs.next()) {
      throw new SQLException("cannot perform add operation.");
    }

    String addressid = rs.getString(AddressInfoColumn.ADDRESS_ID.getName());
    return new AddressId(addressid);
  }

}
