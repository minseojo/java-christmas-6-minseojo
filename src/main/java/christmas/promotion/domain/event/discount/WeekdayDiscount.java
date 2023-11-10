package christmas.promotion.domain.event.discount;

import java.time.LocalDate;

public class WeekdayDiscount implements DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);


    public boolean isBetweenDates(LocalDate currentDate) {
        return !currentDate.isBefore(EVENT_PERIOD_START) && !currentDate.isAfter(EVENT_PERIOD_END);
    }

    @Override
    public double applyDiscount(double price) {
        return 0;
    }
}
