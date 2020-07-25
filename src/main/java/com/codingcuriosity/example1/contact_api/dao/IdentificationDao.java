package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactRes;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import java.util.List;

public interface IdentificationDao {
  List<Identification> findAll();

  List<Contact> findAllDetailed();

  ContactRes insertIdentification(Contact dat);

  void updateIdentification(String contactid, Identification dat);

  public void deleteIdentification(String contactid);

  public void deleteAllIdentification();
}
