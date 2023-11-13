package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.vo.Price;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum SpecialDiscount implements GlobalEvent<Price, Price>, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(2023, 12, 25);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final Price DISCOUNT_PRICE = Price.of(1000.0);  // 수정: double 타입으로 변경
    private static final String EVENT_NAME = "특별 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public boolean isPossibleEvent(LocalDate date, Price price) {
        return isBetweenDates(date) && isSpecialDate(date);
    }

    @Override
    public Price applyEvent(LocalDate date, Price price) {
        if (!isPossibleEvent(date, price)) {
            return Price.zero();
        }

        return DISCOUNT_PRICE;
    }

    @Override
    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isSpecialDate(LocalDate date) {
        return isSunday(date) || isChristmas(date);
    }

    private boolean isSunday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isChristmas(LocalDate date) {
        return date.equals(CHRISTMAS_DATE);
    }
}
