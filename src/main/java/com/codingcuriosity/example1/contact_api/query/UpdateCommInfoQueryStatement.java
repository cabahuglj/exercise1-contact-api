package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;
import java.util.ArrayList;
import java.util.List;

public class UpdateCommInfoQueryStatement extends SqlStatement {
  private final String retField;
  private final String commId;
  private final Communication comm;

  public UpdateCommInfoQueryStatement(String commid, Communication dat) {
    super(CommunicationInfoDbTable.INSTANCE);
    this.retField = CommunicationInfoColumn.CONTACT_ID.getName();
    this.commId = commid;
    this.comm = dat;
  }

  @Override
  public void build() throws QueryFormatException {
    final boolean hasType = (this.comm.getType() != null);
    final boolean hasValue = (this.comm.getValue() != null);

    List<String> stmtSequence = new ArrayList<>();
    String typeValStr = "";
    if (hasType) {
      typeValStr = String.format(SqlStatement.VALFMT, this.comm.getType());
    } else {
      typeValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence
        .add(String.format(MAP_EDIT_VAL, CommunicationInfoColumn.TYPE.getName(), typeValStr));

    String valValStr = "";
    if (hasValue) {
      valValStr = String.format(SqlStatement.VALFMT, this.comm.getValue());
    } else {
      valValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence
        .add(String.format(MAP_EDIT_VAL, CommunicationInfoColumn.VALUE.getName(), valValStr));

    String stmtSeqStr = String.join(",", stmtSequence);
    String commIdColName = CommunicationInfoColumn.COMM_ID.getName();
    String sqlFmt = "UPDATE %s SET %s WHERE %s = \'%s\' RETURNING %s";
    sqlStatement = String.format(sqlFmt, this.table.getName(), stmtSeqStr, commIdColName,
        this.commId, this.retField);
  }

}
