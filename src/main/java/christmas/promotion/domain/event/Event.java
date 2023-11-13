package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;

public interface Event {
    double EVENT_PARTICIPATION_THRESHOLD = 10_000.0;
    String getEventName();

    boolean isBetweenDates(DecemberVisitDate date);
}
