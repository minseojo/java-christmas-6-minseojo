package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public interface Event {
    double EVENT_PARTICIPATION_THRESHOLD = 10_000.0;
    Price NON_DISCOUNT_EVENT = Price.zero();
    Price NON_GIFT_EVENT = Price.zero();
    String getEventName();

    boolean isBetweenDates(DecemberVisitDate date);
}
