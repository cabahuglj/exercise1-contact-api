package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.data.CommunicationDbData;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class EditCommunicationInfoUpdateStatement implements SqlStatement {
  private final static DbTable table = CommunicationInfoDbTable.INSTANCE;
  private String commid = null;
  private String type = null;
  private String value = null;
  private String contactid = null;
  private String sqlStatement = "";

  public EditCommunicationInfoUpdateStatement(CommunicationDbData origResource) {
    if (null == origResource) {
      throw new IllegalArgumentException("param origResource cannot be null.");
    }

    this.commid = origResource.getCommId();
    this.type = origResource.getType();
    this.value = origResource.getValue();
    this.contactid = origResource.getContactId();
  }

  public void setType(String type) {
    this.type = type;
  }


  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public void build() throws QueryFormatException {
    boolean hasType = (this.type != null);
    boolean hasValue = (this.value != null);

    List<String> stmtSequence = new ArrayList<>();
    String typeValStr = "";
    if (hasType) {
      typeValStr = String.format(SqlStatement.VALFMT, this.type);
    } else {
      typeValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence.add(
        String.format(MAP_EDIT_VAL, table.getColumnName(CommunicationInfoColumn.TYPE), typeValStr));
    String valValStr = "";
    if (hasValue) {
      valValStr = String.format(SqlStatement.VALFMT, this.value);
    } else {
      valValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence.add(
        String.format(MAP_EDIT_VAL, table.getColumnName(CommunicationInfoColumn.VALUE), valValStr));
    stmtSequence
        .add(String.format(MAP_EDIT_VAL, table.getColumnName(CommunicationInfoColumn.CONTACT_ID),
            String.format(SqlStatement.VALFMT, this.contactid)));

    String stmtSeqStr = String.join(",", stmtSequence);
    String commIdColName = table.getColumnName(CommunicationInfoColumn.COMM_ID);
    String sqlFmt = "UPDATE %s SET %s WHERE %s = \'%s\';";
    sqlStatement = String.format(sqlFmt, table.getName(), stmtSeqStr, commIdColName, this.commid);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
