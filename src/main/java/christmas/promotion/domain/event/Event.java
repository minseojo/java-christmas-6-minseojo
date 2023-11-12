package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event {
    String getEventName();

    double applyEvent(LocalDate date, double price);

    boolean isBetweenDates(LocalDate date);
}

