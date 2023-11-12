package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.LocalEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum WeekdayDiscount implements LocalEvent, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double DISCOUNT_PRICE = 2023.0;  // 수정: double 타입으로 변경
    private static final String EVENT_NAME = "평일 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }


    @Override
    public boolean isPossibleEvent(LocalDate date) {
        return isBetweenDates(date) && isWeekday(date);
    }

    @Override
    public Double applyEvent(LocalDate date) {
        if (!isPossibleEvent(date)) {
            return 0.0;
        }

        return DISCOUNT_PRICE;
    }

    @Override
    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isWeekday(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }
}