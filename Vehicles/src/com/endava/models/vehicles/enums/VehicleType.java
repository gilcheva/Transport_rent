package com.endava.models.vehicles.enums;

public enum VehicleType {
  LAND(0),
  AIR(1),
  SEA(2);

  int typeOrdinal;

  VehicleType(int ord) {
    this.typeOrdinal = ord;
  }

  public static VehicleType byOrdinal(int ord) {
    for (VehicleType vt: VehicleType.values()) {
      if (vt.ordinal()==ord) {
        return vt;
      }
    }
    throw new IndexOutOfBoundsException ("No VehicleType with such index.");
  }
}
