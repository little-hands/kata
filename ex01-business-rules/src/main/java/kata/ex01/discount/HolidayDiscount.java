package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.util.HolidayUtils;

import java.util.List;

import static kata.ex01.model.HolidayType.HOLIDAY;
import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.RouteType.URBAN;
import static kata.ex01.model.VehicleFamily.STANDARD;

public class HolidayDiscount extends EtcDiscount {

  public HolidayDiscount() {
    super.appliableVehicleFamilies = List.of(STANDARD);
    super.appliableRouteTypes = List.of(RURAL, URBAN);
    super.appliableHolidayTypes = List.of(HOLIDAY);
  }

  private boolean isAppliable(HighwayDrive highwayDrive) {
    return
        isAppliableVehicleFamily(highwayDrive.getVehicleFamily())
            && isAppliableRouteType(highwayDrive.getRouteType())
            && (HolidayUtils.isHoliday(highwayDrive.getEnteredAt().toLocalDate())
            || HolidayUtils.isHoliday(highwayDrive.getExitedAt().toLocalDate()));
  }

  @Override
  public long calcDiscountRate(HighwayDrive highwayDrive) {
    if (isAppliable(highwayDrive)) {
      return 30;
    }
    return 0;
  }

}
