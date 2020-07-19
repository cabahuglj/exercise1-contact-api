package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteCommPrefUpdateStatement implements SqlStatement {
  private final static DbTable table = CommPrefDbTable.INSTANCE;
  private String contactid = null;
  private String sqlStatement = "";

  public void setContactId(String contactid) {
    this.contactid = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID must need to be validated before queried to DB
    boolean isContactIdValid = SqlUtil.isValidUuid(this.contactid);
    if (!isContactIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "contactid", "contactid", this.contactid);
      throw new QueryFormatException(errMsg);
    }

    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\';";
    sqlStatement = String.format(sqlFmt, table.getName(),
        table.getColumnName(CommPrefColumn.CONTACT_ID), this.contactid);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
