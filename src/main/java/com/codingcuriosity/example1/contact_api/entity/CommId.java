package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommId {
  private final String commId;

  public CommId(String commid) {
    this.commId = commid;
  }

  @JsonProperty("commId")
  public String getCommId() {
    return this.commId;
  }
}
