package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import java.util.List;

public interface CommunicationService {
  List<Communication> findAll(String contactid);

  CommId insertCommunication(String contactid, Communication dat);

  void updateCommunication(String commid, Communication dat);

  void deleteCommunication(String commid);

  void deleteAllCommunication(String contactid);
}
