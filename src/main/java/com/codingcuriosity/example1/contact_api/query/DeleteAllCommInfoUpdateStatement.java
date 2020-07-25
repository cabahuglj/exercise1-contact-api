package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;

public class DeleteAllCommInfoUpdateStatement extends SqlDelAllStatement {

  public DeleteAllCommInfoUpdateStatement() {
    super(CommunicationInfoDbTable.INSTANCE);
  }
}
