package com.codingcuriosity.example1.contact_api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
  private final Identification identification;
  private final List<Address> addresses;
  private final List<Communication> communications;

  public Contact(Identification identification, List<Address> addresses,
      List<Communication> communications) {

    this.identification = identification;
    if (null != addresses) {
      this.addresses = addresses.stream().collect(Collectors.toCollection(ArrayList::new));
    } else {
      this.addresses = new ArrayList<>();
    }

    if (null != communications) {
      this.communications =
          communications.stream().collect(Collectors.toCollection(ArrayList::new));
    } else {
      this.communications = new ArrayList<>();
    }
  }

  @JsonProperty("Identification")
  public final Identification getIdentification() {
    return this.identification;
  }

  @JsonProperty("Address")
  public final List<Address> getAddresses() {
    return this.addresses.stream().collect(Collectors.toCollection(ArrayList::new));
  }

  @JsonProperty("Communication")
  public final List<Communication> getCommunications() {
    return this.communications.stream().collect(Collectors.toCollection(ArrayList::new));
  }
}
