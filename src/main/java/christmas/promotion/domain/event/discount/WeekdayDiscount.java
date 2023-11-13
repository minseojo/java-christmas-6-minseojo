package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.VisitDate;

import java.time.LocalDate;

public enum WeekdayDiscount implements LocalEvent, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final Price DISCOUNT_PRICE = Price.of(2023.0);  // 수정: double 타입으로 변경
    private static final String EVENT_NAME = "평일 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Price applyEvent(VisitDate date) {
        if (!isPossibleEvent(date)) {
            return Price.zero();
        }

        return DISCOUNT_PRICE;
    }

    @Override
    public boolean isPossibleEvent(VisitDate date) {
        return isBetweenDates(date) && date.isWeekday();
    }

    @Override
    public boolean isBetweenDates(VisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }
}