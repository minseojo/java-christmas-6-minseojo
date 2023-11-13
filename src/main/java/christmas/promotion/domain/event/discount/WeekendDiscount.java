package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.vo.Price;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum WeekendDiscount implements LocalEvent, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final Price DISCOUNT_PRICE = Price.of(2023.0);  // 수정: double 타입으로 변경
    private static final String EVENT_NAME = "주말 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Price applyEvent(LocalDate date) {
        if (!isPossibleEvent(date)) {
            return Price.zero();
        }

        return DISCOUNT_PRICE;
    }

    @Override
    public boolean isPossibleEvent(LocalDate date) {
        return isBetweenDates(date) && isWeekend(date);
    }

    @Override
    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}