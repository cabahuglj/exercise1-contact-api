package com.codingcuriosity.example1.contact_api.validation;

import java.util.regex.Pattern;

public class CommonCheck {

  private static final Pattern UUIDPTRN =
      Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
  private static final int UUIDLEN = 36;

  private CommonCheck() {}

  public static boolean isValidUuid(String uuid) {
    if (null == uuid || uuid.length() != UUIDLEN) {
      return false;
    } else if (!UUIDPTRN.matcher(uuid).matches()) {
      return false;
    }
    return true;
  }
}
