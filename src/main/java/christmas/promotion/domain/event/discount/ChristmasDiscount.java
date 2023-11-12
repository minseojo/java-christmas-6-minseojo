package christmas.promotion.domain.event.discount;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChristmasDiscount implements DiscountEvent {
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
    public double applyEvent(LocalDate date, double price) {
        int daysUntilChristmas = calculateDaysUntilChristmas(date);
        return STARTING_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * daysUntilChristmas);
    }

    @Override
    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }
    private int calculateDaysUntilChristmas(LocalDate date) {
        return (int) ChronoUnit.DAYS.between(EVENT_PERIOD_START, date);
    }
}
