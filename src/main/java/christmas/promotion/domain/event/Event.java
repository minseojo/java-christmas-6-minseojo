package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event {
    double EVENT_PARTICIPATION_COST = 10_000.0;
    String getEventName();

    boolean isBetweenDates(LocalDate date);
}
