package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import com.codingcuriosity.example1.contact_api.mapper.AddressIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddAddressInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteGrpAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateAddressInfoUpdateStatement;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

  @Autowired
  NamedParameterJdbcTemplate template;

  public AddressDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Address> findAll(String contactid) {
    return DaoCommon.findAllAddress(template, contactid);
  }

  @Override
  public AddressId insertAddress(String contactid, Address dat) {
    SqlStatement stAdd = new AddAddressInfoQueryStatement(contactid, dat);
    String sql = DaoCommon.buildSql(stAdd);
    return template.query(sql, new AddressIdResultSetExtractor());
  }

  @Override
  public void updateAddress(String addressid, Address dat) {
    SqlStatement stUpdate = new UpdateAddressInfoUpdateStatement(addressid, dat);
    String sql = DaoCommon.buildSql(stUpdate);
    template.update(sql, new HashMap<>());
  }

  @Override
  public void deleteAddress(String addressid) {
    SqlStatement stDelete = new DeleteAddressInfoUpdateStatement(addressid);
    String sql = DaoCommon.buildSql(stDelete);
    template.update(sql, new HashMap<>());
  }

  @Override
  public void deleteAllAddress(String contactid) {
    // Delete All Address Info from DB for the specified contact
    SqlStatement stDeleteGrp = new DeleteGrpAddressInfoUpdateStatement(contactid);
    String sql = DaoCommon.buildSql(stDeleteGrp);
    template.update(sql, new HashMap<>());
  }
}
