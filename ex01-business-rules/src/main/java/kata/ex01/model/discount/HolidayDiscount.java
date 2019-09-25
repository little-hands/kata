package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.PeriodOfTime;

import java.util.List;

import static kata.ex01.model.DayType.HOLIDAY;
import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.*;

public class HolidayDiscount extends EtcDiscount {

  public HolidayDiscount() {
    super.appliableVehicleFamilies = List.of(STANDARD, MINI, MOTORCYCLE);
    super.appliableRouteTypes = List.of(RURAL);
    super.appliableDayTypes = List.of(HOLIDAY);
    super.appliablePeriodOfTimes = List.of(PeriodOfTime.values());
  }

  private boolean isAppliable(HighwayDrive highwayDrive) {
    return
        isAppliableVehicleFamily(highwayDrive.getVehicleFamily())
            && isAppliableRouteType(highwayDrive.getRouteType())
            && (isApplicablePassingTime(highwayDrive.getEnteredAt())
            || isApplicablePassingTime(highwayDrive.getExitedAt()));
  }


  @Override
  public long calcDiscountRate(HighwayDrive highwayDrive) {
    if (isAppliable(highwayDrive)) {
      return 30;
    }
    return 0;
  }

}
