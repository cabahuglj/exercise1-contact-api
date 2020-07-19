package com.codingcuriosity.example1.contact_api.jdbc.data;

public final class AddressDbData {
  private final String addressid;
  private final String type;
  private final int number;
  private final String street;
  private final String unit;
  private final String city;
  private final String state;
  private final String zipCode;
  private final String contactid;

  AddressDbData(AddressDbDataBuilder bldr) {
    this.addressid = bldr.addressid;
    this.type = bldr.type;
    this.number = bldr.number;
    this.street = bldr.street;
    this.unit = bldr.unit;
    this.city = bldr.city;
    this.state = bldr.state;
    this.zipCode = bldr.zipCode;
    this.contactid = bldr.contactid;
  }

  public final String getAddressId() {
    return this.addressid;
  }

  public final String getType() {
    return this.type;
  }

  public final int getNumber() {
    return this.number;
  }

  public final String getStreet() {
    return this.street;
  }

  public final String getUnit() {
    return this.unit;
  }

  public final String getCity() {
    return this.city;
  }

  public final String getState() {
    return this.state;
  }

  public final String getZipCode() {
    return this.zipCode;
  }

  public final String getContactId() {
    return this.contactid;
  }
}
