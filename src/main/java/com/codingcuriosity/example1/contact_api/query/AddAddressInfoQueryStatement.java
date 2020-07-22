package com.codingcuriosity.example1.contact_api.query;

import java.util.ArrayList;
import java.util.List;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable;
import com.codingcuriosity.example1.contact_api.db.AddressInfoDbTable.AddressInfoColumn;
import com.codingcuriosity.example1.contact_api.entity.Address;
import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public class AddAddressInfoQueryStatement extends SqlStatement {
  private final String retField;
  private final String contactId;
  private final Address address;

  public AddAddressInfoQueryStatement(String contactid, Address dat) {
    super(AddressInfoDbTable.INSTANCE);
    this.retField = AddressInfoColumn.ADDRESS_ID.getName();
    this.contactId = contactid;
    this.address = dat;
  }

  @Override
  public void build() throws QueryFormatException {
    boolean hasType = (this.address.getType() != null);
    boolean hasNumber = (this.address.getNumber() > 0);
    boolean hasStreet = (this.address.getStreet() != null);
    boolean hasUnit = (this.address.getUnit() != null);
    boolean hasCity = (this.address.getCity() != null);
    boolean hasState = (this.address.getState() != null);
    boolean hasZipCode = (this.address.getZipCode() != null);

    List<String> idSequence = new ArrayList<>();
    List<String> valAddressSequence = new ArrayList<>();
    List<String> valSequence = new ArrayList<>();

    if (hasType) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getType()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasNumber) {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, this.address.getNumber()));
    } else {
      valAddressSequence.add(String.format(SqlStatement.INTVALFMT, 0));
    }

    if (hasStreet) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getStreet()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasUnit) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getUnit()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasCity) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getCity()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasState) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getState()));
    } else {
      valAddressSequence.add(SqlStatement.EMPTYCOMPVAL);
    }

    if (hasZipCode) {
      valAddressSequence.add(String.format(SqlStatement.COMPVALFMT, this.address.getZipCode()));
    } else {
      valAddressSequence.add(String.format(SqlStatement.EMPTYCOMPVAL));
    }

    String addressValSeqStr = String.join(",", valAddressSequence);
    String addressValFmt = "\'(%s)\'";

    idSequence.add(AddressInfoColumn.ADDRESS.getName());
    valSequence.add(String.format(addressValFmt, addressValSeqStr));

    idSequence.add(AddressInfoColumn.CONTACT_ID.getName());
    valSequence.add(String.format(SqlStatement.VALFMT, this.contactId));

    String idSeqStr = String.join(",", idSequence);
    String valSeqStr = String.join(",", valSequence);

    String sqlFmt = "INSERT INTO %s ( %s ) VALUES ( %s ) RETURNING %s";
    sqlStatement = String.format(sqlFmt, this.table.getName(), idSeqStr, valSeqStr, this.retField);
  }
}
