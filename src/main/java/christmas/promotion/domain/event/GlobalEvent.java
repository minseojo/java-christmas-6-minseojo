package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface GlobalEvent extends Event {
    @Override
    double applyEvent(LocalDate date, double price);
}
