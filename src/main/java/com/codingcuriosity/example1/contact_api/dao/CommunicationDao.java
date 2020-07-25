package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import java.util.List;

public interface CommunicationDao {
  public abstract List<Communication> findAll(String contactid);

  public abstract CommId insertCommunication(String contactid, Communication dat);

  public abstract void updateCommunication(String commid, Communication dat);

  public abstract void deleteCommunication(String commid);

  public abstract void deleteAllCommunication(String contactid);
}
