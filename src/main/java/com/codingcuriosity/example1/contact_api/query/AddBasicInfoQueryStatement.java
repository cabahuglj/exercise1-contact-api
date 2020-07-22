package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Identification;

public class AddBasicInfoQueryStatement extends SqlStatement {
  private final String retField;
  private final Identification id;

  public AddBasicInfoQueryStatement(Identification identification) {
    super(BasicInfoDbTable.INSTANCE);
    this.retField = BasicInfoColumn.CONTACT_ID.getName();
    this.id = identification;
  }

  @Override
  public void build() {
    boolean hasFirstName = (this.id.getFirstName() != null);
    boolean hasLastName = (this.id.getLastName() != null);
    boolean hasBirthdate = (this.id.getDob() != null);
    boolean hasGender = (this.id.getGender() != null);
    boolean hasTitle = (this.id.getTitle() != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(BasicInfoColumn.FIRST_NAME.getName());
    if (hasFirstName) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.id.getFirstName()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(BasicInfoColumn.LAST_NAME.getName());
    if (hasLastName) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.id.getLastName()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(BasicInfoColumn.BIRTH_DATE.getName());
    if (hasBirthdate) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.id.getDob()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(BasicInfoColumn.GENDER.getName());
    if (hasGender) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.id.getGender()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(BasicInfoColumn.TITLE.getName());
    if (hasTitle) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.id.getTitle()));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING %s";
    sqlStatement = String.format(sqlFmt, this.table.getName(), idSeqStr, valSeqStr, this.retField);
  }
}
