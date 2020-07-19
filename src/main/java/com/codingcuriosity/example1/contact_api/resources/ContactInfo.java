package com.codingcuriosity.example1.contact_api.resources;


public class ContactInfo {
  private final Identification identification;
  private final Address[] addresses;
  private final Communication[] communications;

  public ContactInfo(Identification identification, Address[] addresses,
      Communication[] communications) {
    this.identification = identification;
    if (null != addresses) {
      this.addresses = new Address[addresses.length];
      System.arraycopy(addresses, 0, this.addresses, 0, addresses.length);
    } else {
      this.addresses = new Address[0];
    }
    if (null != communications) {
      this.communications = new Communication[communications.length];
      System.arraycopy(communications, 0, this.communications, 0, communications.length);
    } else {
      this.communications = new Communication[0];
    }
  }

  public Identification getIdentification() {
    return this.identification;
  }

  public Address[] getAddress() {
    return this.addresses;
  }

  public Communication[] getCommunication() {
    return this.communications;
  }
}
