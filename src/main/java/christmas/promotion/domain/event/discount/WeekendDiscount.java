package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum WeekendDiscount implements Event, LocalEvent, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double DISCOUNT_PRICE = 2023;

    @Override
    public String getEventName() {
        return "주말 할인";
    }

    @Override
    public double applyEvent(LocalDate date, double price){
        if (!isBetweenDates(date)) {
            return NO_DISCOUNT;
        }

        if (!isWeekend(date)) {
            return NO_DISCOUNT;
        }

        return DISCOUNT_PRICE;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

}
