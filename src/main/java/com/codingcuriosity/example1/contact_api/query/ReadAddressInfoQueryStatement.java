package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressCompColumn;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class ReadAddressInfoQueryStatement extends SqlStatement {
  private final String contactId;

  public ReadAddressInfoQueryStatement(String contactid) {
    super(AddressInfoDbTable.INSTANCE);
    this.contactId = contactid;
  }

  public ReadAddressInfoQueryStatement() {
    super(AddressInfoDbTable.INSTANCE);
    this.contactId = null;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "SELECT %s,(address).%s,(address).%s,(address).%s,(address).%s,(address).%s,"
        + "(address).%s,(address).%s,%s FROM %s";

    if (null != this.contactId) {
      setCondition(AddressInfoColumn.CONTACT_ID, this.contactId);
    }

    if ("".equals(this.getCondition())) {
      sqlStatement = String.format(sqlFmt, AddressInfoColumn.ADDRESS_ID.getName(),
          AddressCompColumn.TYPE.getName(), AddressCompColumn.NUMBER.getName(),
          AddressCompColumn.STREET.getName(), AddressCompColumn.UNIT.getName(),
          AddressCompColumn.CITY.getName(), AddressCompColumn.STATE.getName(),
          AddressCompColumn.ZIP_CODE.getName(), AddressInfoColumn.CONTACT_ID.getName(),
          this.table.getName());
    } else {
      sqlFmt += " WHERE ( %s )";
      sqlStatement = String.format(sqlFmt, AddressInfoColumn.ADDRESS_ID.getName(),
          AddressCompColumn.TYPE.getName(), AddressCompColumn.NUMBER.getName(),
          AddressCompColumn.STREET.getName(), AddressCompColumn.UNIT.getName(),
          AddressCompColumn.CITY.getName(), AddressCompColumn.STATE.getName(),
          AddressCompColumn.ZIP_CODE.getName(), AddressInfoColumn.CONTACT_ID.getName(),
          this.table.getName(), this.getCondition());
    }
  }
}
