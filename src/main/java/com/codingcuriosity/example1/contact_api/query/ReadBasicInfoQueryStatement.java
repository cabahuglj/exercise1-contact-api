package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.BasicInfoDbTable.BasicInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Identification;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class ReadBasicInfoQueryStatement extends SqlStatement {

  public ReadBasicInfoQueryStatement() {
    super(BasicInfoDbTable.INSTANCE);
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "SELECT %s,%s,%s,to_char(%s, \'%s\') AS %s,%s,%s FROM %s";
    if ("".equals(this.getCondition())) {
      sqlStatement = String.format(sqlFmt, BasicInfoColumn.CONTACT_ID.getName(),
          BasicInfoColumn.FIRST_NAME.getName(), BasicInfoColumn.LAST_NAME.getName(),
          BasicInfoColumn.BIRTH_DATE.getName(), Identification.DATE_FMT,
          BasicInfoColumn.BIRTH_DATE.getName(), BasicInfoColumn.GENDER.getName(),
          BasicInfoColumn.TITLE.getName(), this.table.getName());
    } else {
      sqlFmt += " WHERE ( %s )";
      sqlStatement = String.format(sqlFmt, BasicInfoColumn.CONTACT_ID.getName(),
          BasicInfoColumn.FIRST_NAME.getName(), BasicInfoColumn.LAST_NAME.getName(),
          BasicInfoColumn.BIRTH_DATE.getName(), Identification.DATE_FMT,
          BasicInfoColumn.GENDER.getName(), BasicInfoColumn.TITLE.getName(), this.table.getName(),
          this.getCondition());
    }
  }
}
