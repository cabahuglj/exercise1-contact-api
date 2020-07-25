package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteGrpCommPrefUpdateStatement extends SqlStatement {
  private final String contactId;

  public DeleteGrpCommPrefUpdateStatement(String contactid) {
    super(CommPrefDbTable.INSTANCE);
    this.contactId = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(), CommPrefColumn.CONTACT_ID.getName(),
        this.contactId);
  }

}
