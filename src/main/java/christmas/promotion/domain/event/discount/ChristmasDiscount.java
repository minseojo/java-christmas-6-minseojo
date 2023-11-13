package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.GlobalEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChristmasDiscount implements GlobalEvent<Double, Double>, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);
    private static final int STARTING_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREMENT = 100;
    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }


    @Override
    public boolean isPossibleEvent(LocalDate date, Double price) {
        return isBetweenDates(date);
    }

    @Override
    public Double applyEvent(LocalDate date, Double price) {
        if (!isPossibleEvent(date, price)) {
            return 0.0;
        }

        int daysUntilChristmas = calculateDaysUntilChristmas(date);
        return (double) (STARTING_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * daysUntilChristmas));
    }

    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private int calculateDaysUntilChristmas(LocalDate date) {
        return (int) ChronoUnit.DAYS.between(EVENT_PERIOD_START, date);
    }
}
