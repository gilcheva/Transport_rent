package com.endava.models;

import com.endava.models.contracts.Course;
import com.endava.models.contracts.Rent;
import com.endava.models.helpers.ValidationHelper;
import java.math.BigDecimal;

public class RentImpl implements Rent {
  private BigDecimal additionalCosts;
  private Course course;

  public RentImpl(Course course, Double additionalCosts) {
    setAdditionalCosts(BigDecimal.valueOf(additionalCosts));
    setCourse(course);
  }

  @Override
  public BigDecimal getAdditionalCosts() {
    return additionalCosts;
  }

  @Override
  public Course getCourse() {
    return course;
  }

  @Override
  public BigDecimal calculatePrice() {
    return getAdditionalCosts().add(getCourse().calculateTransportCosts());
  }

  private void setAdditionalCosts(BigDecimal additionalCosts) {
    this.additionalCosts = additionalCosts;
  }

  private void setCourse(Course course) {
    ValidationHelper.validateNotNull(course);
    this.course = course;
  }

  @Override
  public String toString() {
    return System.lineSeparator() +
        "Destination: " + course.getDestination() + System.lineSeparator() +
        String.format("Price: %.2f", calculatePrice()) + System.lineSeparator();
  }
}
