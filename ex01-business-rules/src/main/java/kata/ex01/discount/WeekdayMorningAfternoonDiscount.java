package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.util.HolidayUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static kata.ex01.model.HolidayType.HOLIDAY;
import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.RouteType.URBAN;
import static kata.ex01.model.VehicleFamily.STANDARD;

public class WeekdayMorningAfternoonDiscount extends EtcDiscount {

  public WeekdayMorningAfternoonDiscount() {
    super.appliableVehicleFamilies = List.of(STANDARD);
    super.appliableRouteTypes = List.of(RURAL, URBAN);
    super.appliableHolidayTypes = List.of(HOLIDAY);
  }

  private boolean isAppliable(HighwayDrive highwayDrive) {
    return
        isAppliableVehicleFamily(highwayDrive.getVehicleFamily())
            && isAppliableRouteType(highwayDrive.getRouteType())
            && !HolidayUtils.isHoliday(highwayDrive.getEnteredAt().toLocalDate())
            && !HolidayUtils.isHoliday(highwayDrive.getExitedAt().toLocalDate());
  }

  @Override
  public long calcDiscountRate(HighwayDrive highwayDrive) {
    if (isAppliable(highwayDrive)) {
      return UsingCountDiscountPattern.of(highwayDrive.getDriver().getCountPerMonth()).discountRate;
    }
    return 0;
  }


  private enum UsingCountDiscountPattern {
    lessThan5(0, 0),
    lessThan10(5, 30),
    greaterEqual10(10, 50);

    private int minimumCount;
    private long discountRate;


    UsingCountDiscountPattern(int minimumCount, long discountRate) {
      this.minimumCount = minimumCount;
      this.discountRate = discountRate;
    }

    static UsingCountDiscountPattern of(int usingCount) {
      return Stream.of(UsingCountDiscountPattern.values())
          .sorted(Comparator.comparing(UsingCountDiscountPattern::getMinimumCount, Comparator.reverseOrder()))
          .filter(pattern -> pattern.minimumCount <= usingCount)
          .findFirst().get();
    }

    public int getMinimumCount() {
      return minimumCount;
    }
  }
}
