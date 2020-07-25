package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable;

public class DeleteAllAddressInfoUpdateStatement extends SqlDelAllStatement {

  public DeleteAllAddressInfoUpdateStatement() {
    super(AddressInfoDbTable.INSTANCE);
  }
}
