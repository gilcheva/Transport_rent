package com.endava.models.helpers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

  public static void validateLimits(double value, double minValue, double maxValue,
      String errMessageFormat) {
    if (!(value >= minValue && value <= maxValue)) {
      throw new IllegalArgumentException(String.format(errMessageFormat, minValue, maxValue));
    }
  }

  public static void validateNotNull(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException();
    }
  }

  public static String dateFormat(OffsetDateTime time) {
    String date = time.toString();
    OffsetDateTime dt = OffsetDateTime.parse(date);
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd' T 'HH:mm");
    return fmt.format(dt);
  }

}
