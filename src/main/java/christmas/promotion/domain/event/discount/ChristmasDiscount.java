package christmas.promotion.domain.event.discount;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDiscount implements DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);
    private static final int STARTING_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREMENT = 100;

    @Override
    public double applyDiscount(double price) {
//        if (isBetweenDates()) {
            int daysUntilChristmas = calculateDaysUntilChristmas();
            double discountAmount = STARTING_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * daysUntilChristmas);
            return discountAmount;
//        }

       // return 0;
    }

    private int calculateDaysUntilChristmas() {
        return (int) ChronoUnit.DAYS.between(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }
//
//    public boolean isBetweenDates(Bill bill) {
//        return isBetweenDates(bill);
//    }
}
