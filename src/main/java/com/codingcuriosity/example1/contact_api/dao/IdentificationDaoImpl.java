package com.codingcuriosity.example1.contact_api.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.mapper.ContactIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddBasicInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteBasicInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateBasicInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

@Repository
public class IdentificationDaoImpl implements IdentificationDao {
  @Autowired
  NamedParameterJdbcTemplate template;

  public IdentificationDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Identification> findAll() {
    return Common.findAllIdentification(template);
  }

  @Override
  public List<Contact> findAllDetailed() {
    List<Identification> idList = Common.findAllIdentification(template);

    List<Contact> contactList = new ArrayList<>();
    List<Address> tmpAddrList;
    List<Communication> tmpCommList;
    for (Identification id : idList) {
      tmpAddrList = Common.findAllAddress(template, id.getContactId());
      tmpCommList = Common.findAllCommunication(template, id.getContactId());
      contactList.add(new Contact(id, tmpAddrList, tmpCommList));
    }

    return contactList;
  }

  @Override
  public ContactId insertIdentification(Identification dat) {
    String sql = "";
    SqlStatement stAdd = new AddBasicInfoQueryStatement(dat);
    try {
      stAdd.build();
      sql = stAdd.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    return template.query(sql, new ContactIdResultSetExtractor());
  }

  @Override
  public void updateIdentification(String contactid, Identification dat) {
    String sql = "";
    SqlStatement stUpdate = new UpdateBasicInfoUpdateStatement(contactid, dat);
    try {
      stUpdate.build();
      sql = stUpdate.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }

    template.update(sql, new HashMap<String, Object>());
  }

  @Override
  public void deleteIdentification(String contactid) {
    String sql = "";

    SqlStatement stDelete = new DeleteBasicInfoUpdateStatement(contactid);
    try {
      stDelete.build();
      sql = stDelete.getSqlStatement();
    } catch (QueryFormatException e) {
      e.printStackTrace();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(BasicInfoColumn.CONTACT_ID.getName(), contactid);

    template.update(sql, new HashMap<String, Object>());
  }
}
