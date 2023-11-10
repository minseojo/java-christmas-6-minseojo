package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.menu.Beverage;

import java.time.LocalDate;

public class ChampagneGift implements GiftEvent{
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double THRESHOLD = 120_000.0;

    @Override
    public double applyGift(LocalDate date, double price){
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
