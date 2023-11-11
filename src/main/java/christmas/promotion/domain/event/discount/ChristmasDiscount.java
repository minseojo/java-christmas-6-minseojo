package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDiscount implements Event, DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);
    private static final int STARTING_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREMENT = 100;

    @Override
    public double applyDiscount(LocalDate date, double price) {
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
