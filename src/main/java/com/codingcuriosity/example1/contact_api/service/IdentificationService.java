package com.codingcuriosity.example1.contact_api.service;

import java.util.List;
import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.Identification;

public interface IdentificationService {
  List<Identification> findAll();

  List<Contact> findAllDetailed();

  void insertIdentification(Identification dat);

  void updateIdentification(String contactid, Identification dat);

  void deleteIdentification(String contactid);
}
