package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.SqlUtil;
import com.codingcuriosity.example1.contact_api.jdbc.data.AddressDbData;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddAddressInfoQueryStatement implements SqlStatement {
  private final static DbTable table = AddressInfoDbTable.INSTANCE;
  private final static String retField = table.getColumnName(AddressInfoColumn.ADDRESS_ID);
  private AddressDbData addressData;
  private String sqlStatement = "";

  public AddAddressInfoQueryStatement(AddressDbData newResource) {
    if (null == newResource) {
      throw new IllegalArgumentException("param newResource cannot be null.");
    }
    addressData = newResource;
  }

  @Override
  public void build() throws QueryFormatException {
    // UUID must need to be validated before written to DB
    boolean isContactIdValid = SqlUtil.isValidUuid(addressData.getContactId());
    if (!isContactIdValid) {
      String errMsg =
          String.format(ERR_UUID_FMT, "contactid", "contactid", addressData.getContactId());
      throw new QueryFormatException(errMsg);
    }

    boolean hasType = (addressData.getType() != null);
    boolean hasNumber = (addressData.getNumber() > 0);
    boolean hasStreet = (addressData.getStreet() != null);
    boolean hasUnit = (addressData.getUnit() != null);
    boolean hasCity = (addressData.getCity() != null);
    boolean hasState = (addressData.getState() != null);
    boolean hasZipCode = (addressData.getZipCode() != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valAddressSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    if (hasType) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getType()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasNumber) {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, addressData.getNumber()));
    } else {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, 0));
    }

    if (hasStreet) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getStreet()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasUnit) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getUnit()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasCity) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getCity()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasState) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getState()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasZipCode) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, addressData.getZipCode()));
    } else {
      valAddressSequence.add(String.format(SqlStatement.EMPTYCOMPVAL));
    }

    String addressValSeqStr = String.join(",", valAddressSequence);
    String addressValFmt = "\'(%s)\'";

    idSequence.add(table.getColumnName(AddressInfoColumn.ADDRESS));
    valSequence.add(String.format(addressValFmt, addressValSeqStr));

    idSequence.add(table.getColumnName(AddressInfoColumn.CONTACT_ID));
    valSequence.add(String.format(SqlStatement.VALFMT, addressData.getContactId()));

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
