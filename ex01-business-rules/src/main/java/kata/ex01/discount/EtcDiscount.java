package kata.ex01.discount;

import kata.ex01.model.*;

import java.util.List;

public abstract class EtcDiscount {
  List<VehicleFamily> appliableVehicleFamilies;
  List<RouteType> appliableRouteTypes;
  List<DayType> appliableDayTypes;
  List<PeriodOfTime> appliablePeriodOfTimes;

  boolean isAppliableVehicleFamily(VehicleFamily vehicleFamily) {
    return appliableVehicleFamilies.contains(vehicleFamily);
  }

  boolean isAppliableRouteType(RouteType routeType) {
    return appliableRouteTypes.contains(routeType);
  }

  private boolean isAppliableDayType(DayType dayType) {
    return appliableDayTypes.contains(dayType);
  }

  private boolean isAppliablePeriodOfTime(PeriodOfTime periodOfTime) {
    return appliablePeriodOfTimes.contains(periodOfTime);
  }

  boolean isApplicablePassingTime(PassingTime passingTime) {
    return isAppliableDayType(passingTime.getDayType())
        && isAppliablePeriodOfTime(passingTime.getPeriodOfTime());
  }


  public abstract long calcDiscountRate(HighwayDrive highwayDrive);


}
