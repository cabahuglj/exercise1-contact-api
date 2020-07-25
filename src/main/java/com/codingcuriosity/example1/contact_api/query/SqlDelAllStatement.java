package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

class SqlDelAllStatement extends SqlStatement {

  SqlDelAllStatement(DbTable table) {
    super(table);
  }

  @Override
  public void build() throws QueryFormatException {
    String sqlFmt = "DELETE FROM %s";
    sqlStatement = String.format(sqlFmt, this.table.getName());
  }

}
