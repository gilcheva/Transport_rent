package com.endava.models.courses.impl;

import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.helpers.Helper;

public class RentImpl implements Rent {
  private double additionalCosts;
  private Course course;

  public RentImpl(Course course, Double additionalCosts) {
    setAdditionalCosts(additionalCosts);
    setCourse(course);
  }

  @Override
  public double getAdditionalCosts() {
    return additionalCosts;
  }

  @Override
  public Course getCourse() {
    return course;
  }

  @Override
  public double calculatePrice() {
    return getAdditionalCosts()+getCourse().calculateTransportCosts();
  }

  private void setAdditionalCosts(double additionalCosts) {
    this.additionalCosts = additionalCosts;
  }

  private void setCourse(Course course) {
    Helper.validateNotNull(course);
    this.course = course;
  }

  @Override
  public String toString() {
    return System.lineSeparator() +
        "Destination: " + course.getDestination() + System.lineSeparator() +
        String.format("Price: %.2f", calculatePrice()) + System.lineSeparator();
  }
}
