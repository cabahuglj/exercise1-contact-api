package com.codingcuriosity.example1.contact_api.jdbc.data;

public final class CommunicationDbData {
  private final String commid;
  private final String type;
  private final String value;
  private final String contactId;


  CommunicationDbData(CommunicationDbDataBuilder bldr) {
    this.commid = bldr.commid;
    this.type = bldr.type;
    this.value = bldr.value;
    this.contactId = bldr.contactId;
  }

  public final String getCommId() {
    return this.commid;
  }

  public final String getType() {
    return this.type;
  }

  public final String getValue() {
    return this.value;
  }

  public final String getContactId() {
    return this.contactId;
  }
}
