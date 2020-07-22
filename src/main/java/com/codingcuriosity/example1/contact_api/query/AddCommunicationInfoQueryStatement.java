package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddCommunicationInfoQueryStatement extends SqlStatement {
  private final String retField;
  private final String contactId;
  private final Communication comm;

  public AddCommunicationInfoQueryStatement(String contactid, Communication dat) {
    super(CommunicationInfoDbTable.INSTANCE);
    this.retField = CommunicationInfoColumn.COMM_ID.getName();
    this.contactId = contactid;
    this.comm = dat;
  }

  @Override
  public void build() throws QueryFormatException {
    boolean hasType = (this.comm.getType() != null);
    boolean hasValue = (this.comm.getValue() != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(CommunicationInfoColumn.TYPE.getName());
    if (hasType) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.comm.getType()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(CommunicationInfoColumn.VALUE.getName());
    if (hasValue) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.comm.getValue()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(CommunicationInfoColumn.CONTACT_ID.getName());
    valSequence.add(String.format(SqlStatement.VALFMT, this.contactId));

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING %s";
    sqlStatement = String.format(sqlFmt, table.getName(), idSeqStr, valSeqStr, this.retField);
  }
}
