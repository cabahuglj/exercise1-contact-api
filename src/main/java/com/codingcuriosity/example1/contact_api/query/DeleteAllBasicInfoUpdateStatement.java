package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable;

public class DeleteAllBasicInfoUpdateStatement extends SqlDelAllStatement {

  public DeleteAllBasicInfoUpdateStatement() {
    super(BasicInfoDbTable.INSTANCE);
  }
}
