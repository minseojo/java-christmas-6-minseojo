package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event {
    String getEventName();

    boolean isBetweenDates(LocalDate date);
}
