package com.endava.models.vehicles.enums;

import static org.junit.jupiter.api.Assertions.*;

import com.endava.models.CourseImpl;
import com.endava.models.contracts.Course;
import org.junit.jupiter.api.Test;

class VehicleTypeTest {

  @Test
  public void method_should_returnCorrectType_when_validIndexGiven(){
    assertEquals(VehicleType.byOrdinal(0), VehicleType.LAND);
    assertEquals(VehicleType.byOrdinal(1), VehicleType.AIR);
    assertEquals(VehicleType.byOrdinal(2), VehicleType.SEA);
  }

  @Test
  public void constructor_should_throw_when_indexOutOfRange() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      VehicleType vt = VehicleType.byOrdinal(3);
    });
  }

}