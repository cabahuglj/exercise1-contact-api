package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteCommInfoUpdateStatement extends SqlStatement {
  private final String commId;

  public DeleteCommInfoUpdateStatement(String commid) {
    super(CommunicationInfoDbTable.INSTANCE);
    this.commId = commid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(),
        CommunicationInfoColumn.COMM_ID.getName(), this.commId);
  }
}
