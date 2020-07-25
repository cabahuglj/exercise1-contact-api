package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.dao.AddressDao;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceImpl implements AddressService {

  @Resource
  AddressDao addressDao;

  @Override
  public List<Address> findAll(String contactid) {
    return addressDao.findAll(contactid);
  }

  @Override
  public AddressId insertAddress(String contactid, Address dat) {
    return addressDao.insertAddress(contactid, dat);
  }

  @Override
  public void updateAddress(String addressid, Address dat) {
    addressDao.updateAddress(addressid, dat);
  }

  @Override
  public void deleteAddress(String addressid) {
    addressDao.deleteAddress(addressid);
  }

  @Override
  public void deleteAllAddress(String contactid) {
    addressDao.deleteAllAddress(contactid);
  }
}
