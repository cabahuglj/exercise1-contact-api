package com.codingcuriosity.example1.contact_api.service;

import java.util.List;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;

public interface AddressService {
  List<Address> findAll(String contactid);

  AddressId insertAddress(String contactid, Address dat);

  void updateAddress(String addressid, Address dat);

  void deleteAddress(String addressid);
}
