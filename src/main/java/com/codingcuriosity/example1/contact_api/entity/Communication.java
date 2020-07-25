package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Communication {
  private final String commid;
  private final String type;
  private final String value;
  private final String contactid;
  private boolean preferred;

  public Communication(String commid, String type, String value, String contactid,
      boolean preferred) {
    this.commid = commid;
    this.type = type;
    this.value = value;
    this.contactid = contactid;
    this.preferred = preferred;
  }

  public final void setPreferred() {
    this.preferred = true;
  }

  @JsonIgnore
  @JsonProperty("commid")
  public final String getCommId() {
    return this.commid;
  }

  @JsonProperty("type")
  @JsonAlias({"Type"})
  public final String getType() {
    return this.type;
  }

  @JsonProperty("value")
  @JsonAlias({"Value"})
  public final String getValue() {
    return this.value;
  }

  @JsonIgnore
  @JsonProperty("contactid")
  public final String getContactId() {
    return this.contactid;
  }

  @JsonProperty(value = "preferred", required = false)
  @JsonAlias({"Preferred"})
  public final boolean isPreferred() {
    return this.preferred;
  }
}
