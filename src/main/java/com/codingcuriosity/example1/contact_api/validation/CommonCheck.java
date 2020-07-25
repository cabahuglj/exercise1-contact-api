package com.codingcuriosity.example1.contact_api.validation;

import java.util.regex.Pattern;

public class CommonCheck {

  private static final Pattern UUIDPTRN =
      Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

  private CommonCheck() {}

  public static boolean isValidUuid(String uuid) {
    return (!UUIDPTRN.matcher(uuid).matches());
  }
}
