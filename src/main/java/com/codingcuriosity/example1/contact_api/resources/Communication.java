package com.codingcuriosity.example1.contact_api.resources;

public class Communication {
  private final String type;
  private final String value;
  private final boolean preferred;

  public Communication(String type, String value, boolean preferred) {
    this.type = type;
    this.value = value;
    this.preferred = preferred;
  }

  public final String getType() {
    return this.type;
  }

  public final String getValue() {
    return this.value;
  }

  public final boolean isPreferred() {
    return this.preferred;
  }
}
