package christmas.promotion.domain.event.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(2023, 12, 25);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double DISCOUNT_PRICE = 1000;

    @Override
    public double applyDiscount(LocalDate date, double price){
        if (!isBetweenDates(date)) {
            return NO_DISCOUNT;
        }

        if (!isSpecialDate(date)) {
            return NO_DISCOUNT;
        }

        return DISCOUNT_PRICE;
    }

    private boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isSpecialDate(LocalDate date) {
        return isSunday(date) || isChristmas(date);
    }

    private boolean isSunday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isChristmas(LocalDate date) {
        return date == CHRISTMAS_DATE;
    }
}
