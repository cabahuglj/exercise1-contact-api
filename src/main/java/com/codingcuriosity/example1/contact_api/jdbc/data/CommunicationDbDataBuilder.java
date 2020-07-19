package com.codingcuriosity.example1.contact_api.jdbc.data;

public class CommunicationDbDataBuilder {
  String commid;
  String type;
  String value;
  String contactId;

  public CommunicationDbDataBuilder setCommId(String commid) {
    this.commid = commid;
    return this;
  }

  public CommunicationDbDataBuilder setType(String type) {
    this.type = type;
    return this;
  }

  public CommunicationDbDataBuilder setValue(String value) {
    this.value = value;
    return this;
  }

  public CommunicationDbDataBuilder setContactId(String contactId) {
    this.contactId = contactId;
    return this;
  }

  public CommunicationDbData build() {
    return new CommunicationDbData(this);
  }
}
