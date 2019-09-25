package kata.ex01.model;

import kata.ex01.discount.WeekdayMorningAfternoonDiscount;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 時間区分
 */
@AllArgsConstructor
public enum PeriodOfTime {
  MIDNIGHT(0, 4),
  EARLY_MORNING(4, 6),
  MORNING(6, 9),
  NOON(9, 17),
  EVENING(17, 20),
  NIGHT(20, 24);
  private int startHour;
  private int endHour;

  static PeriodOfTime of(int hour) {
    return Stream.of(PeriodOfTime.values())
        .filter(period -> period.startHour <= hour && hour < period.endHour)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
