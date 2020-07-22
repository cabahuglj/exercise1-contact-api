package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
  private final String addressid;
  private final String type;
  private final int number;
  private final String street;
  private final String unit;
  private final String city;
  private final String state;
  private final String zipCode;
  private final String contactId;

  public Address(String addressid, String type, int number, String street, String unit, String city,
      String state, String zipCode, String contactid) {
    this.addressid = addressid;
    this.type = type;
    this.number = number;
    this.street = street;
    this.unit = unit;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.contactId = contactid;
  }

  @JsonIgnore
  @JsonProperty("addressid")
  public final String getAddressId() {
    return this.addressid;
  }

  @JsonProperty("type")
  public final String getType() {
    return this.type;
  }

  @JsonProperty("number")
  public final int getNumber() {
    return this.number;
  }

  @JsonProperty("street")
  public final String getStreet() {
    return this.street;
  }

  @JsonProperty("Unit")
  public final String getUnit() {
    return this.unit;
  }

  @JsonProperty("City")
  public final String getCity() {
    return this.city;
  }

  @JsonProperty("State")
  public final String getState() {
    return this.state;
  }

  @JsonProperty("zipcode")
  public final String getZipCode() {
    return this.zipCode;
  }

  @JsonIgnore
  @JsonProperty("contactid")
  public final String getContactId() {
    return this.contactId;
  }
}
