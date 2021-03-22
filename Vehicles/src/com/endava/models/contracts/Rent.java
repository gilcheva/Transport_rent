package com.endava.models.contracts;

import java.math.BigDecimal;

public interface Rent {

  BigDecimal getAdditionalCosts();

  Course getCourse();

  BigDecimal calculatePrice();
}
