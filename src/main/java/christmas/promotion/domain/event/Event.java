package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface Event {
    double applyEvent(LocalDate date, double price);
}

