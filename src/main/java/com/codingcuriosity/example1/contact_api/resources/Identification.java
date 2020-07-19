package com.codingcuriosity.example1.contact_api.resources;

public class Identification {
  private final String firstName;
  private final String lastName;
  private final String dob;
  private final String gender;
  private final String title;

  public Identification(String firstName, String lastName, String dob, String gender,
      String title) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
    this.title = title;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getDob() {
    return this.dob;
  }

  public String getGender() {
    return this.gender;
  }

  public String getTitle() {
    return this.title;
  }
}
