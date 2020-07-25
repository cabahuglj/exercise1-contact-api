package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.db.DbColumn;
import com.codingcuriosity.example1.contact_api.db.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public abstract class SqlStatement implements SqlBuildable {

  public enum ConditionOper {
    OR("OR"), //
    AND("AND");

    private final String val;

    private ConditionOper(String val) {
      this.val = val;
    }

    String getCond() {
      return this.val;
    }
  }

  static class ConditionItem {
    private String condition;

    ConditionItem(String key, String val) {
      this.condition = String.format("%s = \'%s\'", key, val);
    }

    ConditionItem(String condition) {
      this.condition = condition;
    }

    String getText() {
      return condition;
    }
  }

  static class CompoundConditionItem {
    private ConditionItem cond1;
    private ConditionItem cond2;
    private ConditionOper oper;

    CompoundConditionItem(ConditionItem cond1, ConditionItem cond2, ConditionOper oper) {
      this.cond1 = cond1;
      this.cond2 = cond2;
      this.oper = oper;
    }

    String getText() {
      return String.format("%s %s %s", this.cond1.getText(), this.oper.getCond(),
          this.cond2.getText());
    }
  }

  // Empty Value (Standard Text-based Type)
  static final String EMPTYVAL = "\'\'";

  // Empty Value (Composite Data Type)
  static final String EMPTYCOMPVAL = "\"\"";

  // Value Format for Standard Text-based Types
  static final String VALFMT = "\'%s\'";

  // Value Format for Composite Data Types
  static final String COMPVALFMT = "\"%s\"";

  // Value Format for Integer-based Data Types
  static final String INTVALFMT = "%d";

  // Error Format for UUID related errors
  static final String ERR_UUID_FMT = "%s provided is not a valid UUID. %s = %s";

  // Map format for SQL statements related to UPDATE
  static final String MAP_EDIT_VAL = "%s = %s";

  final DbTable table;
  String sqlStatement = "";
  private ConditionItem condition = null;

  SqlStatement(DbTable table) {
    if (null == table) {
      throw new IllegalArgumentException("parameter table cannot be null.");
    }
    this.table = table;
  }

  // Utility method to build a multiple condition
  private void setMultCondition(Enum<? extends DbColumn> key, String val,
      ConditionOper operFromPrevCond) {
    ConditionItem condition2 = new ConditionItem(this.table.getColumnName(key), val);
    CompoundConditionItem compCond =
        new CompoundConditionItem(this.condition, condition2, operFromPrevCond);
    this.condition = new ConditionItem(compCond.getText());
  }

  String getCondition() {
    return ((null != this.condition) ? this.condition.getText() : "");
  }

  public String getSqlStatement() {
    return this.sqlStatement;
  }

  /**
   * Sets a single condition.
   * 
   * @param key DB table column name.
   * @param val DB table row value for the given column name.
   * @throws QueryFormatException when <code>key</code> is <code>null</code>.
   */
  public void setCondition(Enum<? extends DbColumn> key, String val) throws QueryFormatException {
    if (null == key) {
      throw new QueryFormatException("condition key cannot be null.");
    }

    this.condition = new ConditionItem(this.table.getColumnName(key), val);
  }

  /**
   * Appends an additional condition to the previous condition.
   * 
   * @param key DB table column name.
   * @param val DB table row value for the given column name.
   * @param operFromPrevCond operation of the new condition to the existing condition. Example: If
   *        previous condition is <code>contactid = 'abc'</code>, and key, value, and
   *        operFromPrevCond are 'commid', 'def', and ' OR respectively, the resulting condition
   *        becomes <code>contactid = 'abc' OR commid = 'def'</code>
   * @throws QueryFormatException when <code>key</code> is <code>null</code>.
   */
  public void setCondition(Enum<? extends DbColumn> key, String val, ConditionOper operFromPrevCond)
      throws QueryFormatException {
    if (null == this.condition) {
      setCondition(key, val);
    } else {
      if (null == key) {
        throw new QueryFormatException("condition key can neither be null nor blank");
      } else if (null == operFromPrevCond) {
        throw new QueryFormatException("param operFromPrevCond cannot be null");
      }
      setMultCondition(key, val, operFromPrevCond);
    }
  }
}
