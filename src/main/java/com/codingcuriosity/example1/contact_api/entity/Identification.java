package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Identification {
  public static final String DATE_FMT = "MM/DD/YYYY";
  private final String contactid;
  private final String firstName;
  private final String lastName;
  private final String dob;
  private final String gender;
  private final String title;

  public Identification(String contactid, String firstName, String lastName, String dob,
      String gender, String title) {
    this.contactid = contactid;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
    this.title = title;
  }

  @JsonIgnore
  @JsonProperty("contactid")
  public String getContactId() {
    return this.contactid;
  }

  @JsonProperty("FirstName")
  public String getFirstName() {
    return this.firstName;
  }

  @JsonProperty("LastName")
  public String getLastName() {
    return this.lastName;
  }

  @JsonProperty("DOB")
  public String getDob() {
    return this.dob;
  }

  @JsonProperty(value = "Gender", required = false)
  public String getGender() {
    return this.gender;
  }

  @JsonProperty("Title")
  public String getTitle() {
    return this.title;
  }
}
