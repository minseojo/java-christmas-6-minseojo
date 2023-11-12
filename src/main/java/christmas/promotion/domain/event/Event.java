package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event<T> {
    String getEventName();

    T applyEvent(LocalDate date, T value);

    boolean isBetweenDates(LocalDate date);
}
