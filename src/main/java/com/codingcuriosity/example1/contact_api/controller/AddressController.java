package com.codingcuriosity.example1.contact_api.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.entity.AddressId;
import com.codingcuriosity.example1.contact_api.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

  @Resource
  AddressService addressService;

  @GetMapping(value = "/getAll/{contactId}")
  public List<Address> getAddresses(@PathVariable(value = "contactId") String contactId) {
    return addressService.findAll(contactId);
  }

  @PostMapping(value = "/add/{contactId}")
  public AddressId createAddress(@PathVariable(value = "contactId") String contactId,
      @RequestBody Address dat) {
    return addressService.insertAddress(contactId, dat);
  }

  @PutMapping(value = "/update/{addressId}")
  public void updateAddress(@PathVariable(value = "addressId") String addressId,
      @RequestBody Address dat) {
    addressService.updateAddress(addressId, dat);
  }

  @DeleteMapping(value = "/delete/{addressId}")
  public void deleteAddress(@PathVariable(value = "addressId") String addressId) {
    addressService.deleteAddress(addressId);
  }
}
