package com.endava.models.helpers;

public class ValidationHelper {

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

}
