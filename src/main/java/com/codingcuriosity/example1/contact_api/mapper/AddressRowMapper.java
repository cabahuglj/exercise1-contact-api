package com.codingcuriosity.example1.contact_api.mapper;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressCompColumn;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AddressRowMapper implements RowMapper<Address> {

  @Override
  public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
    String addressid = rs.getString(AddressInfoColumn.ADDRESS_ID.getName());
    String type = rs.getString(AddressCompColumn.TYPE.getName());
    int number = rs.getInt(AddressCompColumn.NUMBER.getName());
    String street = rs.getString(AddressCompColumn.STREET.getName());
    String unit = rs.getString(AddressCompColumn.UNIT.getName());
    String city = rs.getString(AddressCompColumn.CITY.getName());
    String state = rs.getString(AddressCompColumn.STATE.getName());
    String zipCode = rs.getString(AddressCompColumn.ZIP_CODE.getName());
    String contactid = rs.getString(AddressInfoColumn.CONTACT_ID.getName());
    return new Address(addressid, type, number, street, unit, city, state, zipCode, contactid);
  }

}
