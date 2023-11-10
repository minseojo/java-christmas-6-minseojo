package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.Bill;

import java.time.LocalDate;

public class SpecialDiscount implements DiscountEvent {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);


    @Override
    public double applyDiscount(double price) {
        return 0;
    }

    public boolean isBetweenDates(Bill bill) {
        return bill.isEventDiscountApplicable(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }

}
