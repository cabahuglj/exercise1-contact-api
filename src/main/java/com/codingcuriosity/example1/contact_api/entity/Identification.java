package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
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

  @JsonProperty(value = "id", required = false)
  public String getContactId() {
    return this.contactid;
  }

  @JsonProperty("FirstName")
  @JsonAlias({"firstname", "firstName"})
  public String getFirstName() {
    return this.firstName;
  }

  @JsonProperty("LastName")
  @JsonAlias({"lastname", "lastName"})
  public String getLastName() {
    return this.lastName;
  }

  @JsonProperty("DOB")
  @JsonAlias({"dob"})
  public String getDob() {
    return this.dob;
  }

  @JsonProperty(value = "Gender", required = false)
  @JsonAlias({"gender"})
  public String getGender() {
    return this.gender;
  }

  @JsonProperty("Title")
  @JsonAlias({"title"})
  public String getTitle() {
    return this.title;
  }
}
