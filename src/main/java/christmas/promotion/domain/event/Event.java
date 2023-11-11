package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event {
    default String getEventName() {
        return "이벤트";
    }

    double applyEvent(LocalDate date, double price);

    default boolean isBetweenDates(LocalDate date) {
        return false;
    }
}

