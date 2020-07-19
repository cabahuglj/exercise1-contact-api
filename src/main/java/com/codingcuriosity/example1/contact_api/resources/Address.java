package com.codingcuriosity.example1.contact_api.resources;

public final class Address {
  private final String type;
  private final int number;
  private final String street;
  private final String unit;
  private final String city;
  private final String state;
  private final String zipCode;

  public Address(String type, int number, String street, String unit, String city, String state,
      String zipCode) {
    this.type = type;
    this.number = number;
    this.street = street;
    this.unit = unit;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
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
}
