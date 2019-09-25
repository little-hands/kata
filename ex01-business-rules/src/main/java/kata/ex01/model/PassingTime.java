package kata.ex01.model;

import kata.ex01.util.HolidayUtils;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class PassingTime {
  private LocalDateTime value;

  public DayType getDayType() {
    if (HolidayUtils.isHoliday(this.value.toLocalDate())) {
      return DayType.HOLIDAY;
    }
    return DayType.WEEKDAY;
  }

  public PeriodOfTime getPeriodOfTime() {
    return PeriodOfTime.of(value.getHour());
  }
}
