package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactId {
  private final String id;

  public ContactId(String contactId) {
    this.id = contactId;
  }

  @JsonProperty("contactId")
  public String getContactId() {
    return this.id;
  }
}
