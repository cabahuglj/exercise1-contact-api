package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class DeleteCommunicationInfoUpdateStatement implements SqlStatement {
  private final static DbTable table = CommunicationInfoDbTable.INSTANCE;
  private String commid = null;
  private String sqlStatement = "";

  public void setCommId(String addressid) {
    this.commid = addressid;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID must need to be validated before queried to DB
    boolean isCommIdValid = SqlUtil.isValidUuid(this.commid);
    if (!isCommIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "commid", "commid", this.commid);
      throw new QueryFormatException(errMsg);
    }

    String sqlFmt = "DELETE FROM %s WHERE %s = \'%s\';";
    sqlStatement = String.format(sqlFmt, table.getName(),
        table.getColumnName(CommunicationInfoColumn.COMM_ID), this.commid);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
