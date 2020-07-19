package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddCommunicationInfoQueryStatement implements SqlStatement {
  private final static DbTable table = CommunicationInfoDbTable.INSTANCE;
  private final static String retField = table.getColumnName(CommunicationInfoColumn.COMM_ID);
  private String type = null;
  private String value = null;
  private String contactid = null;
  private String sqlStatement = "";

  public void setType(String type) {
    this.type = type;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setContactId(String contactid) {
    this.contactid = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID must need to be validated before written to DB
    boolean isContactIdValid = SqlUtil.isValidUuid(this.contactid);
    if (!isContactIdValid) {
      String errMsg = String.format(ERR_UUID_FMT, "contactid", "contactid", this.contactid);
      throw new QueryFormatException(errMsg);
    }

    boolean hasType = (this.type != null);
    boolean hasValue = (this.value != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(table.getColumnName(CommunicationInfoColumn.TYPE));
    if (hasType) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.type));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(CommunicationInfoColumn.VALUE));
    if (hasValue) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.value));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(CommunicationInfoColumn.CONTACT_ID));
    valSequence.add(String.format(SqlStatement.VALFMT, this.contactid));

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING %s;";
    sqlStatement = String.format(sqlFmt, table.getName(), idSeqStr, valSeqStr, retField);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
