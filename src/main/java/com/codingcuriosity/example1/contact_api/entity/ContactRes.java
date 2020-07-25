package com.codingcuriosity.example1.contact_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactRes {
  private final ContactId contactId;
  private final List<AddressId> addressIdList;
  private final List<CommId> commIdList;

  public ContactRes(ContactId contactId, List<AddressId> addressIdList, List<CommId> commIdList) {
    this.contactId = contactId;
    if (null != addressIdList) {
      this.addressIdList = addressIdList.stream().collect(Collectors.toCollection(ArrayList::new));
    } else {
      this.addressIdList = null;
    }
    if (null != commIdList) {
      this.commIdList = commIdList.stream().collect(Collectors.toCollection(ArrayList::new));
    } else {
      this.commIdList = null;
    }
  }

  @JsonProperty("basic-info")
  public final ContactId getContactId() {
    return this.contactId;
  }

  @JsonProperty("addresses")
  public final List<AddressId> getAddressIdList() {
    return this.addressIdList;
  }

  @JsonProperty("communications")
  public final List<CommId> getCommIdList() {
    return this.commIdList;
  }
}
