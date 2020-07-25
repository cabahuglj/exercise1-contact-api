package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable;

public class DeleteAllCommPrefUpdateStatement extends SqlDelAllStatement {

  public DeleteAllCommPrefUpdateStatement() {
    super(CommPrefDbTable.INSTANCE);
  }
}
