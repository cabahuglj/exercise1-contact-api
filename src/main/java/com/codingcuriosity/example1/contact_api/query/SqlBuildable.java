package com.codingcuriosity.example1.contact_api.query;

import com.codingcuriosity.example1.contact_api.query.exception.QueryFormatException;

public interface SqlBuildable {

  public abstract void build() throws QueryFormatException;
}
