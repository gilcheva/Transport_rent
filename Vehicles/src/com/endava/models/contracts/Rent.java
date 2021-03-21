package com.endava.models.contracts;

public interface Rent {
  double getAdditionalCosts();

  Course getCourse();

  double calculatePrice();
}
