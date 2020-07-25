package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import com.codingcuriosity.example1.contact_api.entity.ContactRes;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.mapper.AddressIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.mapper.CommIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.mapper.ContactIdResultSetExtractor;
import com.codingcuriosity.example1.contact_api.query.AddAddressInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddBasicInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommInfoQueryStatement;
import com.codingcuriosity.example1.contact_api.query.AddCommPrefQueryStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAllAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAllBasicInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAllCommInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteAllCommPrefUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteBasicInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteGrpAddressInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteGrpCommInfoUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.DeleteGrpCommPrefUpdateStatement;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;
import com.codingcuriosity.example1.contact_api.query.UpdateBasicInfoUpdateStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationDaoImpl implements IdentificationDao {
  @Autowired
  NamedParameterJdbcTemplate template;

  public IdentificationDaoImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<Identification> findAll() {
    return DaoCommon.findAllIdentification(template);
  }

  @Override
  public List<Contact> findAllDetailed() {
    List<Identification> idList = DaoCommon.findAllIdentification(template);

    List<Contact> contactList = new ArrayList<>();
    List<Address> tmpAddrList;
    List<Communication> tmpCommList;
    for (Identification id : idList) {
      tmpAddrList = DaoCommon.findAllAddress(template, id.getContactId());
      tmpCommList = DaoCommon.findAllCommunication(template, id.getContactId());
      contactList.add(new Contact(id, tmpAddrList, tmpCommList));
    }

    return contactList;
  }

  @Override
  public ContactRes insertIdentification(Contact dat) {
    Identification id = dat.getIdentification();
    List<Address> addrList = dat.getAddresses();
    List<Communication> commList = dat.getCommunications();

    // Insert Identification info to DB
    SqlStatement stAdd = new AddBasicInfoQueryStatement(id);
    String sql = DaoCommon.buildSql(stAdd);
    ContactId retContact = template.query(sql, new ContactIdResultSetExtractor());

    /*
     * Improvement point: will be using template.batchUpdate in the future. However right now, using
     * batchUpdate does not work; the ":value" does not change to the actual value. It may have
     * something to do with my spring boot configuration, which still needs additional investigation
     * time.
     */
    // Insert all Address info to DB
    List<AddressId> retAddrList = new ArrayList<>();
    AddressId retAddr;
    for (Address addr : addrList) {
      stAdd = new AddAddressInfoQueryStatement(retContact.getContactId(), addr);
      sql = DaoCommon.buildSql(stAdd);
      retAddr = template.query(sql, new AddressIdResultSetExtractor());
      retAddrList.add(retAddr);
    }

    // Insert all Communication info to DB
    List<CommId> retCommlist = new ArrayList<>();
    CommId retComm;
    for (Communication comm : commList) {
      stAdd = new AddCommInfoQueryStatement(retContact.getContactId(), comm);
      sql = DaoCommon.buildSql(stAdd);
      retComm = template.query(sql, new CommIdResultSetExtractor());

      if (null == retComm) {
        continue;
      }

      retCommlist.add(retComm);

      // Also insert to CommPref if communication is preferred.
      if (comm.isPreferred()) {
        stAdd = new AddCommPrefQueryStatement(retContact.getContactId(), retComm.getCommId());
        sql = DaoCommon.buildSql(stAdd);
        template.query(sql, new CommIdResultSetExtractor());
      }
    }

    return new ContactRes(retContact, retAddrList, retCommlist);
  }

  @Override
  public void updateIdentification(String contactid, Identification dat) {
    SqlStatement stUpdate = new UpdateBasicInfoUpdateStatement(contactid, dat);
    String sql = DaoCommon.buildSql(stUpdate);
    template.update(sql, new HashMap<>());
  }

  @Override
  public void deleteIdentification(String contactid) {
    SqlStatement stDelete = new DeleteBasicInfoUpdateStatement(contactid);
    String sql = DaoCommon.buildSql(stDelete);
    template.update(sql, new HashMap<>());

    SqlStatement stDeleteGrp = new DeleteGrpAddressInfoUpdateStatement(contactid);
    sql = DaoCommon.buildSql(stDeleteGrp);
    template.update(sql, new HashMap<>());

    stDeleteGrp = new DeleteGrpCommInfoUpdateStatement(contactid);
    sql = DaoCommon.buildSql(stDeleteGrp);
    template.update(sql, new HashMap<>());

    stDeleteGrp = new DeleteGrpCommPrefUpdateStatement(contactid);
    sql = DaoCommon.buildSql(stDeleteGrp);
    template.update(sql, new HashMap<>());
  }

  @Override
  public void deleteAllIdentification() {
    SqlStatement stDeleteAll = new DeleteAllBasicInfoUpdateStatement();
    String sql = DaoCommon.buildSql(stDeleteAll);
    template.update(sql, new HashMap<>());

    stDeleteAll = new DeleteAllAddressInfoUpdateStatement();
    sql = DaoCommon.buildSql(stDeleteAll);
    template.update(sql, new HashMap<>());

    stDeleteAll = new DeleteAllCommInfoUpdateStatement();
    sql = DaoCommon.buildSql(stDeleteAll);
    template.update(sql, new HashMap<>());

    stDeleteAll = new DeleteAllCommPrefUpdateStatement();
    sql = DaoCommon.buildSql(stDeleteAll);
    template.update(sql, new HashMap<>());
  }
}
