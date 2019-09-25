package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static kata.ex01.model.DayType.WEEKDAY;
import static kata.ex01.model.PeriodOfTime.EVENING;
import static kata.ex01.model.PeriodOfTime.MORNING;
import static kata.ex01.model.RouteType.RURAL;

public class WeekdayMorningAfternoonDiscount extends EtcDiscount {

  public WeekdayMorningAfternoonDiscount() {
    super.appliableVehicleFamilies = List.of(VehicleFamily.values());
    super.appliableRouteTypes = List.of(RURAL);
    super.appliableDayTypes = List.of(WEEKDAY);
    super.appliablePeriodOfTimes = List.of(MORNING, EVENING);
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
      return UsingCountDiscountPattern.of(highwayDrive.getDriver().getCountPerMonth()).discountRate;
    }
    return 0;
  }


  @AllArgsConstructor
  @Getter
  private enum UsingCountDiscountPattern {
    lessThan5(0, 0),
    lessThan10(5, 30),
    greaterEqual10(10, 50);

    private int minimumCount;
    private long discountRate;

    static UsingCountDiscountPattern of(int usingCount) {
      return Stream.of(UsingCountDiscountPattern.values())
          .sorted(Comparator.comparing(UsingCountDiscountPattern::getMinimumCount, Comparator.reverseOrder()))
          .filter(pattern -> pattern.minimumCount <= usingCount)
          .findFirst().get();
    }

  }
}
