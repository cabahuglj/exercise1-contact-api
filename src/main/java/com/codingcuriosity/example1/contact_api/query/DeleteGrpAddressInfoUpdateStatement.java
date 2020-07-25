package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteGrpAddressInfoUpdateStatement extends SqlStatement {
  private final String contactId;

  public DeleteGrpAddressInfoUpdateStatement(String contactid) {
    super(AddressInfoDbTable.INSTANCE);
    this.contactId = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(),
        AddressInfoColumn.CONTACT_ID.getName(), this.contactId);
  }

}
