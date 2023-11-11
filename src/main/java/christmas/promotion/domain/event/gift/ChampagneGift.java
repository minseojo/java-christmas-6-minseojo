package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.menu.Beverage;

import java.time.LocalDate;

public enum ChampagneGift implements Event, GlobalEvent, GiftEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double THRESHOLD = 120_000.0;

    @Override
    public double applyEvent(LocalDate date, double price){
        if (!isBetweenDates(date)) {
            return NO_DISCOUNT;
        }

        if (!isPriceThresholdAboveOrEqual(price)) {
            return NO_DISCOUNT;
        }

        return Beverage.CHAMPAGNE.getPrice();
    }

    private boolean isPriceThresholdAboveOrEqual(double price) {
        return price >= THRESHOLD;
    }

    private boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

}
