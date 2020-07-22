package com.codingcuriosity.example1.contact_api.dao;

import java.util.List;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;

public interface AddressDao {
  public abstract List<Address> findAll(String contactid);

  public abstract AddressId insertAddress(String contactid, Address dat);

  public abstract void updateAddress(String addressid, Address dat);

  public abstract void deleteAddress(String addressid);
}
