package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.jdbc.defs.BasicInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class EditBasicInfoUpdateStatement implements SqlStatement {
  private final static DbTable table = BasicInfoDbTable.INSTANCE;

  @Override
  public void build() throws QueryFormatException {
    // TODO Auto-generated method stub

  }

  @Override
  public String getSqlStatement() {
    // TODO Auto-generated method stub
    return null;
  }

}
