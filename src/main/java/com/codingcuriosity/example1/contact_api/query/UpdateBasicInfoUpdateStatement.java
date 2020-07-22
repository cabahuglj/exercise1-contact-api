package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class UpdateBasicInfoUpdateStatement extends SqlStatement {
  private final String contactId;
  private final Identification id;

  public UpdateBasicInfoUpdateStatement(String contactid, Identification identification) {
    super(BasicInfoDbTable.INSTANCE);
    this.contactId = contactid;
    this.id = identification;
  }

  @Override
  public void build() throws QueryFormatException {
    boolean hasFirstName = (this.id.getFirstName() != null);
    boolean hasLastName = (this.id.getLastName() != null);
    boolean hasDob = (this.id.getDob() != null);
    boolean hasGender = (this.id.getGender() != null);
    boolean hasTitle = (this.id.getTitle() != null);

    List<String> stmtSequence = new ArrayList<>();
    String firstnameValStr = "";
    if (hasFirstName) {
      firstnameValStr = String.format(SqlStatement.VALFMT, this.id.getFirstName());
    } else {
      firstnameValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence
        .add(String.format(MAP_EDIT_VAL, BasicInfoColumn.FIRST_NAME.getName(), firstnameValStr));

    String lastnameValStr = "";
    if (hasLastName) {
      lastnameValStr = String.format(SqlStatement.VALFMT, this.id.getLastName());
    } else {
      lastnameValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence
        .add(String.format(MAP_EDIT_VAL, BasicInfoColumn.LAST_NAME.getName(), lastnameValStr));

    String dobValStr = "";
    if (hasDob) {
      dobValStr = String.format(SqlStatement.VALFMT, this.id.getDob());
    } else {
      dobValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence.add(String.format(MAP_EDIT_VAL, BasicInfoColumn.BIRTH_DATE.getName(), dobValStr));

    String genderValStr = "";
    if (hasGender) {
      genderValStr = String.format(SqlStatement.VALFMT, this.id.getGender());
    } else {
      genderValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence.add(String.format(MAP_EDIT_VAL, BasicInfoColumn.GENDER.getName(), genderValStr));

    String titleValStr = "";
    if (hasTitle) {
      titleValStr = String.format(SqlStatement.VALFMT, this.id.getTitle());
    } else {
      titleValStr = SqlStatement.EMPTYVAL;
    }
    stmtSequence.add(String.format(MAP_EDIT_VAL, BasicInfoColumn.TITLE.getName(), titleValStr));

    String stmtSeqStr = String.join(",", stmtSequence);
    String contactIdColName = BasicInfoColumn.CONTACT_ID.getName();
    String sqlFmt = "UPDATE %s SET %s WHERE %s = \'%s\'";
    sqlStatement =
        String.format(sqlFmt, this.table.getName(), stmtSeqStr, contactIdColName, this.contactId);
  }
}
