package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteBasicInfoUpdateStatement extends SqlStatement {
  private final String contactId;

  public DeleteBasicInfoUpdateStatement(String contactid) {
    super(BasicInfoDbTable.INSTANCE);
    this.contactId = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(), BasicInfoColumn.CONTACT_ID.getName(),
        this.contactId);
  }
}
