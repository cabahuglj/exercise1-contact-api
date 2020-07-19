package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.defs.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.BasicInfoDbTable.BasicInfoColumn;

public class AddBasicInfoQueryStatement implements SqlStatement {
  private final static DbTable table = BasicInfoDbTable.INSTANCE;
  private final static String retField = table.getColumnName(BasicInfoColumn.CONTACT_ID);
  private String firstname = null;
  private String lastname = null;
  private String birthdate = null;
  private String gender = null;
  private String title = null;
  private String sqlStatement = "";

  public void setFirstName(String firstname) {
    this.firstname = firstname;
  }

  public void setLastName(String lastname) {
    this.lastname = lastname;
  }

  public void setBirthDate(String birthdate) {
    this.birthdate = birthdate;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void build() {
    boolean hasFirstName = (this.firstname != null);
    boolean hasLastName = (this.lastname != null);
    boolean hasBirthdate = (this.birthdate != null);
    boolean hasGender = (this.gender != null);
    boolean hasTitle = (this.title != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    idSequence.add(table.getColumnName(BasicInfoColumn.FIRST_NAME));
    if (hasFirstName) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.firstname));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(BasicInfoColumn.LAST_NAME));
    if (hasLastName) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.lastname));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(BasicInfoColumn.BIRTH_DATE));
    if (hasBirthdate) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.birthdate));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(BasicInfoColumn.GENDER));
    if (hasGender) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.gender));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

    idSequence.add(table.getColumnName(BasicInfoColumn.TITLE));
    if (hasTitle) {
      valSequence.add(String.format(SqlStatement.VALFMT, this.title));
    } else {
      valSequence.add(SqlStatement.EMPTYVAL);
    }

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
