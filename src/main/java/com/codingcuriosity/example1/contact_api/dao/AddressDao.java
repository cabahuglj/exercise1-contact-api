package com.codingcuriosity.example1.contact_api.dao;

import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import java.util.List;

public interface AddressDao {
  public abstract List<Address> findAll(String contactid);

  public abstract AddressId insertAddress(String contactid, Address dat);

  public abstract void updateAddress(String addressid, Address dat);

  public abstract void deleteAddress(String addressid);

  public abstract void deleteAllAddress(String contactid);
}
