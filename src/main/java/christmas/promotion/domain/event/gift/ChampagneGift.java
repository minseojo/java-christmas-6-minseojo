package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.Menu;

import java.time.LocalDate;

public enum ChampagneGift implements GiftEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double THRESHOLD = 120_000.0;
    private static final Menu GIFT_MENU = Beverage.CHAMPAGNE;
    private static final int GIFT_QUANTITY = 1;

    @Override
    public Menu getGiftMenu() {
        return GIFT_MENU;
    }

    @Override
    public Integer getGiftQuantity() {
        return GIFT_QUANTITY;
    }

    @Override
    public double applyEvent(LocalDate date, double price){
        if (!isPriceThresholdAboveOrEqual(price)) {
            return NO_DISCOUNT;
        }

        return GIFT_MENU.getPrice() * GIFT_QUANTITY;
    }

    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isPriceThresholdAboveOrEqual(double price) {
        return price >= THRESHOLD;
    }
}
