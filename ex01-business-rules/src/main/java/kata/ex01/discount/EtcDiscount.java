package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.HolidayType;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;

import java.util.List;

public abstract class EtcDiscount {
  protected List<VehicleFamily> appliableVehicleFamilies;
  protected List<RouteType> appliableRouteTypes;
  protected List<HolidayType> appliableHolidayTypes;

  protected boolean isAppliableVehicleFamily(VehicleFamily vehicleFamily) {
    return appliableVehicleFamilies.contains(vehicleFamily);
  }

  protected boolean isAppliableRouteType(RouteType routeType) {
    return appliableRouteTypes.contains(routeType);
  }

  public abstract long calcDiscountRate(HighwayDrive highwayDrive);


}
