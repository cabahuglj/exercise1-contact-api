package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteGrpCommInfoUpdateStatement extends SqlStatement {
  private final String contactId;

  public DeleteGrpCommInfoUpdateStatement(String contactid) {
    super(CommunicationInfoDbTable.INSTANCE);
    this.contactId = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(),
        CommunicationInfoColumn.CONTACT_ID.getName(), this.contactId);
  }

}
