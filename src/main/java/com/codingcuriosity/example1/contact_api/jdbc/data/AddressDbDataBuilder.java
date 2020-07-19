package com.codingcuriosity.example1.contact_api.jdbc.data;

public class AddressDbDataBuilder {
  String addressid = null;
  String type = null;
  int number = 0;
  String street = null;
  String unit = null;
  String city = null;
  String state = null;
  String zipCode = null;
  String contactid = null;

  public AddressDbDataBuilder setAddressId(String addressid) {
    this.addressid = addressid;
    return this;
  }

  public AddressDbDataBuilder setType(String type) {
    this.type = type;
    return this;
  }

  public AddressDbDataBuilder setNumber(int number) {
    this.number = number;
    return this;
  }

  public AddressDbDataBuilder setStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressDbDataBuilder setUnit(String unit) {
    this.unit = unit;
    return this;
  }

  public AddressDbDataBuilder setCity(String city) {
    this.city = city;
    return this;
  }

  public AddressDbDataBuilder setState(String state) {
    this.state = state;
    return this;
  }

  public AddressDbDataBuilder setZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public AddressDbDataBuilder setContactId(String contactid) {
    this.contactid = contactid;
    return this;
  }

  public AddressDbData build() {
    return new AddressDbData(this);
  }
}
