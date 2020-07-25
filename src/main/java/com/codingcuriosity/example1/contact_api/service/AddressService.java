package com.codingcuriosity.example1.contact_api.service;

import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import java.util.List;

public interface AddressService {
  List<Address> findAll(String contactid);

  AddressId insertAddress(String contactid, Address dat);

  void updateAddress(String addressid, Address dat);

  void deleteAddress(String addressid);

  void deleteAllAddress(String contactid);
}
