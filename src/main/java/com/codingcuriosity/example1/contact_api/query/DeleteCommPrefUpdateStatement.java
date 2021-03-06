package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteCommPrefUpdateStatement extends SqlStatement {
  private final String commId;

  public DeleteCommPrefUpdateStatement(String commid) {
    super(CommPrefDbTable.INSTANCE);
    this.commId = commid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement =
        String.format(sqlFmt, this.table.getName(), CommPrefColumn.COMM_ID.getName(), this.commId);
  }
}
