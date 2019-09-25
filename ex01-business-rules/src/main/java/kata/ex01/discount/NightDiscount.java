package kata.ex01.discount;

import kata.ex01.model.*;

import java.util.List;

public class NightDiscount extends EtcDiscount {

  public NightDiscount() {
    super.appliableVehicleFamilies = List.of(VehicleFamily.values());
    super.appliableRouteTypes = List.of(RouteType.values());
    super.appliableDayTypes = List.of(DayType.values());
    super.appliablePeriodOfTimes = List.of(PeriodOfTime.MIDNIGHT);
  }

  private boolean isAppliable(HighwayDrive highwayDrive) {
    return
        isAppliableVehicleFamily(highwayDrive.getVehicleFamily())
            && isAppliableRouteType(highwayDrive.getRouteType())
            && isApplicablePassingTime(highwayDrive);
  }

  private boolean isApplicablePassingTime(HighwayDrive highwayDrive) {
    // TODO: enterのhourからexitのhourまでをループしてPassingTimeインスタンスを作り、
    // 順次super.isApplicablePassingTimeにわたして1つでもtrueが変えればtrue
    return true;
  }


  @Override
  public long calcDiscountRate(HighwayDrive highwayDrive) {
    if (isAppliable(highwayDrive)) {
      return 30;
    }
    return 0;
  }

}
