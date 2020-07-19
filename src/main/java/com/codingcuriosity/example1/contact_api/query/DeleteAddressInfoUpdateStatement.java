package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteAddressInfoUpdateStatement implements SqlStatement {
  private final static DbTable table = AddressInfoDbTable.INSTANCE;
  private String addressid = null;
  private String sqlStatement = "";

  public void setAddressId(String addressid) {
    this.addressid = addressid;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID must need to be validated before queried to DB
    boolean isAddressIdValid = SqlUtil.isValidUuid(this.addressid);
    if (!isAddressIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "addressid", "addressid", this.addressid);
      throw new QueryFormatException(errMsg);
    }

    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\';";
    sqlStatement = String.format(sqlFmt, table.getName(),
        table.getColumnName(AddressInfoColumn.ADDRESS_ID), this.addressid);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }
}
