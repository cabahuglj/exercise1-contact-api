package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.dao.CommunicationDao;
import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CommunicationServiceImpl implements CommunicationService {

  @Resource
  CommunicationDao commDao;

  @Override
  public List<Communication> findAll(String contactid) {
    return commDao.findAll(contactid);
  }

  @Override
  public CommId insertCommunication(String contactid, Communication dat) {
    return commDao.insertCommunication(contactid, dat);
  }

  @Override
  public void updateCommunication(String commid, Communication dat) {
    commDao.updateCommunication(commid, dat);
  }

  @Override
  public void deleteCommunication(String commid) {
    commDao.deleteCommunication(commid);
  }

  @Override
  public void deleteAllCommunication(String contactid) {
    commDao.deleteAllCommunication(contactid);
  }
}
