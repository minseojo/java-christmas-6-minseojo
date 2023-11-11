package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.GlobalEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChristmasDiscount implements Event, GlobalEvent, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);
    private static final int STARTING_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREMENT = 100;

    @Override
    public double applyEvent(LocalDate date, double price) {
        if (!isBetweenDates(date)) {
            return NO_DISCOUNT;
        }

        int daysUntilChristmas = calculateDaysUntilChristmas(date);
        return STARTING_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * daysUntilChristmas);
    }

    private boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private int calculateDaysUntilChristmas(LocalDate date) {
        return (int) ChronoUnit.DAYS.between(EVENT_PERIOD_START, date);
    }
}
