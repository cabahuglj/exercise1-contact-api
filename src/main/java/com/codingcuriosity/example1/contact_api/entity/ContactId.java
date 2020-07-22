package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactId {
  private final String contactId;

  public ContactId(String contactId) {
    this.contactId = contactId;
  }

  @JsonProperty("contactId")
  public String getContactId() {
    return this.contactId;
  }
}
