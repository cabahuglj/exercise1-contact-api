package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteAddressInfoUpdateStatement extends SqlStatement {
  private final String addressId;

  public DeleteAddressInfoUpdateStatement(String addressid) {
    super(AddressInfoDbTable.INSTANCE);
    this.addressId = addressid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\'";
    sqlStatement = String.format(sqlFmt, this.table.getName(),
        AddressInfoColumn.ADDRESS_ID.getName(), this.addressId);
  }

}
