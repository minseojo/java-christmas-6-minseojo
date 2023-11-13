package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.VisitDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChristmasDiscount implements GlobalEvent<Price, Price>, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);
    private static final double STARTING_DISCOUNT_AMOUNT = 1000;
    private static final double DAILY_DISCOUNT_INCREMENT = 100;
    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Price applyEvent(VisitDate date, Price price) {
        if (!isPossibleEvent(date, price)) {
            return Price.zero();
        }

        int daysUntilChristmas = calculateDaysUntilChristmas(date);
        return Price.of(STARTING_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * daysUntilChristmas));
    }

    @Override
    public boolean isPossibleEvent(VisitDate date, Price price) {
        return isBetweenDates(date) && isPriceAboveMinimumThreshold(price);
    }

    private boolean isPriceAboveMinimumThreshold(Price price) {
        return price.price() >= EVENT_PARTICIPATION_THRESHOLD;
    }

    public boolean isBetweenDates(VisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }

    private int calculateDaysUntilChristmas(VisitDate date) {
        return (int) ChronoUnit.DAYS.between(EVENT_PERIOD_START, date.getVisitDate());
    }
}
