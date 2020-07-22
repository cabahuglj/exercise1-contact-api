package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressId {
  private final String addressId;

  public AddressId(String addressid) {
    this.addressId = addressid;
  }

  @JsonProperty("addressId")
  public String getAddressId() {
    return this.addressId;
  }
}
