package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactRes;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import java.util.List;

public interface IdentificationService {
  List<Identification> findAll();

  List<Contact> findAllDetailed();

  ContactRes insertIdentification(Contact dat);

  void updateIdentification(String contactid, Identification dat);

  void deleteIdentification(String contactid);

  public void deleteAllIdentification();
}
