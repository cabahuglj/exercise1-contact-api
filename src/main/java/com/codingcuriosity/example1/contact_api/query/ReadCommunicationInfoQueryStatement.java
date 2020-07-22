package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable;
import com.codingcuriosity.example1.contact_api.db.CommPrefDbTable.CommPrefColumn;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommInfoTrnsColumn;
import com.codingcuriosity.example1.contact_api.db.CommunicationInfoDbTable.CommunicationInfoColumn;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class ReadCommunicationInfoQueryStatement extends SqlStatement {
  private final String contactId;

  public ReadCommunicationInfoQueryStatement(String contactid) {
    super(CommunicationInfoDbTable.INSTANCE);
    this.contactId = contactid;
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "SELECT rzult.%s, %s, %s, rzult.%s, "
        + "CASE COALESCE(tmp, '00000000-0000-0000-0000-000000000000') "
        + "WHEN '00000000-0000-0000-0000-000000000000' THEN false ELSE true END AS %s "
        + "FROM (SELECT %s,%s,a.%s AS %s,a.%s AS %s,b.%s as tmp FROM %s AS a "
        + "FULL OUTER JOIN %s AS b ON a.%s = b.%s) AS rzult";

    if (null != this.contactId) {
      setCondition(CommunicationInfoColumn.CONTACT_ID, this.contactId);
    }

    if ("".equals(this.getCondition())) {
      sqlStatement = String.format(sqlFmt, CommunicationInfoColumn.COMM_ID.getName(),
          CommunicationInfoColumn.TYPE.getName(), CommunicationInfoColumn.VALUE.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(), CommInfoTrnsColumn.PREFERRED.getName(),
          CommunicationInfoColumn.TYPE.getName(), CommunicationInfoColumn.VALUE.getName(),
          CommunicationInfoColumn.COMM_ID.getName(), CommunicationInfoColumn.COMM_ID.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(), CommPrefColumn.COMM_ID.getName(),
          this.table.getName(), CommPrefDbTable.INSTANCE.getName(),
          CommunicationInfoColumn.COMM_ID.getName(), CommPrefColumn.COMM_ID.getName());
    } else {
      sqlFmt += " WHERE ( %s )";
      sqlStatement = String.format(sqlFmt, CommunicationInfoColumn.COMM_ID.getName(),
          CommunicationInfoColumn.TYPE.getName(), CommunicationInfoColumn.VALUE.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(), CommInfoTrnsColumn.PREFERRED.getName(),
          CommunicationInfoColumn.TYPE.getName(), CommunicationInfoColumn.VALUE.getName(),
          CommunicationInfoColumn.COMM_ID.getName(), CommunicationInfoColumn.COMM_ID.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(),
          CommunicationInfoColumn.CONTACT_ID.getName(), CommPrefColumn.COMM_ID.getName(),
          this.table.getName(), CommPrefDbTable.INSTANCE.getName(),
          CommunicationInfoColumn.COMM_ID.getName(), CommPrefColumn.COMM_ID.getName(),
          this.getCondition());
    }
  }
}
