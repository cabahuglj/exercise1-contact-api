package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.dao.IdentificationDao;
import com.codingcuriosity.example1.contact_api.entity.Contact;
import com.codingcuriosity.example1.contact_api.entity.ContactRes;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class IdentificationServiceImpl implements IdentificationService {
  @Resource
  IdentificationDao identificationDao;

  @Override
  public List<Identification> findAll() {
    return identificationDao.findAll();
  }

  @Override
  public List<Contact> findAllDetailed() {
    return identificationDao.findAllDetailed();
  }

  @Override
  public ContactRes insertIdentification(Contact dat) {
    return identificationDao.insertIdentification(dat);
  }

  @Override
  public void updateIdentification(String contactid, Identification dat) {
    identificationDao.updateIdentification(contactid, dat);
  }

  @Override
  public void deleteIdentification(String contactid) {
    identificationDao.deleteIdentification(contactid);
  }

  @Override
  public void deleteAllIdentification() {
    identificationDao.deleteAllIdentification();
  }
}
