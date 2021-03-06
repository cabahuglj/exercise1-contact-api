package com.codingcuriosity.example1.contact_api.mapper;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AddressIdResultSetExtractor implements ResultSetExtractor<AddressId> {

  @Override
  public AddressId extractData(ResultSet rs) throws SQLException {
    if (!rs.next()) {
      throw new SQLException("cannot perform add operation.");
    }

    String addressid = rs.getString(AddressInfoColumn.ADDRESS_ID.getName());
    return new AddressId(addressid);
  }

}
