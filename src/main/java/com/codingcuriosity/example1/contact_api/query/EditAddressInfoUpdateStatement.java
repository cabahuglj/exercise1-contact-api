package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.jdbc.data.AddressDbData;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.jdbc.defs.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.jdbc.defs.DbTable;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class EditAddressInfoUpdateStatement implements SqlStatement {
  private final static DbTable table = AddressInfoDbTable.INSTANCE;
  private String addressid = null;
  private String type = null;
  private int number = 0;
  private String street = null;
  private String unit = null;
  private String city = null;
  private String state = null;
  private String zipcode = null;
  private String contactid = null;
  private String sqlStatement = "";

  public EditAddressInfoUpdateStatement(AddressDbData origResource) {
    if (null == origResource) {
      throw new IllegalArgumentException("param origResource cannot be null.");
    }

    this.addressid = origResource.getAddressId();
    this.type = origResource.getType();
    this.number = origResource.getNumber();
    this.street = origResource.getStreet();
    this.unit = origResource.getUnit();
    this.city = origResource.getCity();
    this.state = origResource.getState();
    this.zipcode = origResource.getZipCode();
    this.contactid = origResource.getContactId();
  }

  public void setAddressType(String type) {
    this.type = type;
  }

  public void setAddressNumber(int number) {
    this.number = number;
  }

  public void setAddressStreet(String street) {
    this.street = street;
  }

  public void setAddressUnit(String unit) {
    this.unit = unit;
  }

  public void setAddressCity(String city) {
    this.city = city;
  }

  public void setAddressState(String state) {
    this.state = state;
  }

  public void setAddressZipCode(String zipcode) {
    this.zipcode = zipcode;
  }

  @Override
  public void build() throws QueryFormatException {

    boolean hasType = (this.type != null);
    boolean hasNumber = (this.number > 0);
    boolean hasStreet = (this.street != null);
    boolean hasUnit = (this.unit != null);
    boolean hasCity = (this.city != null);
    boolean hasState = (this.state != null);
    boolean hasZipCode = (this.zipcode != null);

    List<String> valAddressSequence = new ArrayList<>();

    if (hasType) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.type));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasNumber) {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, this.number));
    } else {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, 0));
    }

    if (hasStreet) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.street));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasUnit) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.unit));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasCity) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.city));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasState) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.state));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasZipCode) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.zipcode));
    } else {
      valAddressSequence.add(String.format(SqlStatement.EMPTYCOMPVAL));
    }

    String addressValSeqStr = String.join(",", valAddressSequence);
    String addressValFmt = "\'(%s)\'";

    List<String> stmtSequence = new ArrayList<>();
    stmtSequence.add(String.format(MAP_EDIT_VAL, table.getColumnName(AddressInfoColumn.ADDRESS),
        String.format(addressValFmt, addressValSeqStr)));
    stmtSequence.add(String.format(MAP_EDIT_VAL, table.getColumnName(AddressInfoColumn.CONTACT_ID),
        String.format(SqlStatement.VALFMT, this.contactid)));

    String stmtSeqStr = String.join(",", stmtSequence);
    String addrIdColName = table.getColumnName(AddressInfoColumn.ADDRESS_ID);
    String sqlFmt = "UPDATE %s SET %s WHERE %s = \'%s\';";
    sqlStatement =
        String.format(sqlFmt, table.getName(), stmtSeqStr, addrIdColName, this.addressid);
  }

  @Override
  public String getSqlStatement() {
    return sqlStatement;
  }

}
