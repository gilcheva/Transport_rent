package com.endava.models.courses;


public interface Rent {

  double getAdditionalCosts();

  Course getCourse();

  double calculatePrice();
}
