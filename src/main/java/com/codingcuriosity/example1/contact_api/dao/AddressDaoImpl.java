package com.codingcuriosity.example1.contact_api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import com.codingcuriosity.example1.contact_api.mapper.AddressIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddAddressInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

@Repository
public class AddressDaoImpl implements AddressDao {

  @Autowired
  NamedParameterJdbcTemplate template;

  public AddressDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Address> findAll(String contactid) {
    return Common.findAllAddress(template, contactid);
  }

  @Override
  public AddressId insertAddress(String contactid, Address dat) {
    String sql = "";
    SqlStatement stAdd = new AddAddressInfoQueryStatement(contactid, dat);
    try {
      stAdd.build();
      sql = stAdd.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new AddressIdResultSetExtractor());
  }

  @Override
  public void updateAddress(String addressid, Address dat) {
    String sql = "";
    SqlStatement stUpdate = new UpdateAddressInfoUpdateStatement(addressid, dat);
    try {
      stUpdate.build();
      sql = stUpdate.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    template.update(sql, new HashMap<String, Object>());
  }

  @Override
  public void deleteAddress(String addressid) {
    String sql = "";

    SqlStatement stDelete = new DeleteAddressInfoUpdateStatement(addressid);
    try {
      stDelete.build();
      sql = stDelete.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(AddressInfoColumn.ADDRESS_ID.getName(), addressid);

    template.update(sql, new HashMap<String, Object>());
  }

}
