package com.codingcuriosity.example1.contact_api.service;

import java.util.List;
import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;

public interface CommunicationService {
  List<Communication> findAll(String contactid);

  CommId insertCommunication(String contactid, Communication dat);

  void updateCommunication(String commid, Communication dat);

  void deleteCommunication(String commid);
}
