package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddCommPrefQueryStatement implements SqlStatement {
  private final static DbTable table = CommPrefDbTable.INSTANCE;
  private String commid = null;
  private String contactid = null;
  private String sqlStatement = "";

  public void setCommId(String commid) {
    this.commid = commid;
  }

  public void setContactId(String contactid) {
    this.contactid = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID for commid must need to be validated before written to DB
    boolean isCommIdValid = SqlUtil.isValidUuid(this.commid);
    if (!isCommIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "commid", "commid", this.commid);
      throw new QueryFormatException(errMsg);
    }

    // UUID for contactid must need to be validated before written to DB
    boolean isContactIdValid = SqlUtil.isValidUuid(this.contactid);
    if (!isContactIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "contactid", "contactid", this.contactid);
      throw new QueryFormatException(errMsg);
    }

    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(table.getColumnName(CommPrefColumn.COMM_ID));
    valSequence.add(String.format(SqlStatement.VALFMT, this.commid));

    idSequence.add(table.getColumnName(CommPrefColumn.CONTACT_ID));
    valSequence.add(String.format(SqlStatement.VALFMT, this.contactid));

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING 0;";
    sqlStatement = String.format(sqlFmt, table.getName(), idSeqStr, valSeqStr);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
