package kata.ex01;

import kata.ex01.model.discount.EtcDiscount;
import kata.ex01.model.discount.HolidayDiscount;
import kata.ex01.model.discount.NightDiscount;
import kata.ex01.model.discount.WeekdayMorningAfternoonDiscount;
import kata.ex01.model.HighwayDrive;

import java.util.Comparator;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {

  private List<EtcDiscount> applyDiscountCandidate = List.of(
      new WeekdayMorningAfternoonDiscount(),
      new HolidayDiscount(),
      new NightDiscount());

  @Override
  public long calc(HighwayDrive drive) {
    return applyDiscountCandidate.stream()
        .map(candidate -> candidate.calcDiscountRate(drive))
        .max(Comparator.naturalOrder())
        .get();
  }
}
