package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommId {
  private final String id;

  public CommId(String commid) {
    this.id = commid;
  }

  @JsonProperty("commId")
  public String getCommId() {
    return this.id;
  }
}
