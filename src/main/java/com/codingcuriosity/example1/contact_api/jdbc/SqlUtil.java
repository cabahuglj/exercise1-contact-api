package com.codingcuriosity.example1.contact_api.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.BiFunction;
import java.util.regex.Pattern;
import com.codingcuriosity.example1.contact_api.query.QueryResult;
import com.codingcuriosity.example1.contact_api.query.SqlStatement;

public class SqlUtil {

  private static final Pattern UUIDPTRN =
      Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
  private static final int UUIDLEN = 36;

  private SqlUtil() {}

  static boolean checkConnectionPrm(String hostname, int port, String dbName, String username,
      String password) {
    if (null == hostname || "".equals(hostname)) {
      return false;
    } else if (1 < port) {
      return false;
    } else if (null == dbName || "".equals(dbName)) {
      return false;
    } else if (null == username || "".equals(username)) {
      return false;
    } else if (null == password) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean isValidUuid(String uuid) {
    if (null == uuid || uuid.length() != UUIDLEN) {
      return false;
    } else if (!UUIDPTRN.matcher(uuid).matches()) {
      return false;
    }
    return true;
  }

  private static QueryResult executeSql(JdbcSqlConnect sqlConnect, SqlStatement queryStatement,
      BiFunction<String, Statement, QueryResult> sqlExec) {
    if (null == sqlConnect) {
      return new QueryResult(new IllegalArgumentException("sqlConnect cannot be null."));
    } else if (null == queryStatement) {
      return new QueryResult(new IllegalArgumentException("queryStatement cannot be null."));
    }

    try (Connection connection = DriverManager.getConnection(sqlConnect.getDbUrl(),
        sqlConnect.getDbUsername(), sqlConnect.getDbPassword());) {
      if (null == connection) {
        String strMsg = String.format("Could not connect to url \"%s\"", sqlConnect.getDbUrl());
        return new QueryResult(false, strMsg);
      }

      Statement statement = connection.createStatement();
      return sqlExec.apply(queryStatement.getSqlStatement(), statement);
    } catch (SQLException e) {
      return new QueryResult(e);
    }
  }

  public static QueryResult executeQuery(JdbcSqlConnect sqlConnect, SqlStatement queryStatement) {
    return executeSql(sqlConnect, queryStatement, (sql, statement) -> {
      ResultSet resultSet;
      try {
        resultSet = statement.executeQuery(sql);
      } catch (SQLException e) {
        return new QueryResult(e);
      }
      return new QueryResult(resultSet);
    });
  }

  public static QueryResult executeUpdate(JdbcSqlConnect sqlConnect,
      SqlStatement queryStatement) {
    return executeSql(sqlConnect, queryStatement, (sql, statement) -> {
      int result = -1;
      try {
        result = statement.executeUpdate(sql);
      } catch (SQLException e) {
        return new QueryResult(e);
      }
      return new QueryResult(result);
    });
  }
}
