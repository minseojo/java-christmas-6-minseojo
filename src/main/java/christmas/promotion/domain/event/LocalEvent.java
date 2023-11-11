package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface LocalEvent extends Event {
    @Override
    double applyEvent(LocalDate date, double price);
}
