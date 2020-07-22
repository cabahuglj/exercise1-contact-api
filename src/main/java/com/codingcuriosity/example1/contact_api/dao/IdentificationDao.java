package com.codingcuriosity.example1.contact_api.dao;

import java.util.List;
import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactId;
import com.codingcuriosity.example1.contact_api.entity.Identification;

public interface IdentificationDao {
  List<Identification> findAll();

  List<Contact> findAllDetailed();

  ContactId insertIdentification(Identification dat);

  void updateIdentification(String contactid, Identification dat);

  public void deleteIdentification(String contactid);
}
