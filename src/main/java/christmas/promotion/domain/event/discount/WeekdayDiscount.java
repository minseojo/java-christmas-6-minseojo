package christmas.promotion.domain.event.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double DISCOUNT_PRICE = 2023;

    @Override
    public double applyDiscount(LocalDate date, double price){
        if (!isBetweenDates(date)) {
            return NO_DISCOUNT;
        }

        if (!isWeekday(date)) {
            return NO_DISCOUNT;
        }

        return DISCOUNT_PRICE;
    }

    private boolean isWeekday(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    private boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }
}
