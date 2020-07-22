package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddCommPrefQueryStatement extends SqlStatement {
  private final String retField;
  private final String contactId;
  private final String commId;

  public AddCommPrefQueryStatement(String contactid, String commid) {
    super(CommPrefDbTable.INSTANCE);
    this.retField = CommPrefColumn.COMM_ID.getName();
    this.contactId = contactid;
    this.commId = commid;
  }

  @Override
  public void build() throws QueryFormatException {
    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(CommPrefColumn.COMM_ID.getName());
    valSequence.add(String.format(SqlStatement.VALFMT, this.commId));

    idSequence.add(CommPrefColumn.CONTACT_ID.getName());
    valSequence.add(String.format(SqlStatement.VALFMT, this.contactId));

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING %s";
    sqlStatement = String.format(sqlFmt, this.table.getName(), idSeqStr, valSeqStr, this.retField);
  }
}
