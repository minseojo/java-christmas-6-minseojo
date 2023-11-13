package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.Menu;

import java.time.LocalDate;

public enum ChampagneGift implements GlobalEvent<Double, Double>, GiftEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double THRESHOLD = 120_000.0;
    private static final Menu GIFT_MENU = Beverage.CHAMPAGNE;  // 수정: Beverage.CHAMPAGNE -> Menu.CHAMPAGNE
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
    public Double applyEvent(LocalDate date, Double price) {
        if (!isPossibleEvent(date, price)) {
            return 0.0;
        }

        return GIFT_MENU.getPrice() * GIFT_QUANTITY;
    }

    @Override
    public boolean isPossibleEvent(LocalDate date, Double price) {
        return isBetweenDates(date) && isPriceThresholdAboveOrEqual(price);
    }

    @Override
    public boolean isBetweenDates(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    private boolean isPriceThresholdAboveOrEqual(Double price) {
        return price >= THRESHOLD;
    }
}
