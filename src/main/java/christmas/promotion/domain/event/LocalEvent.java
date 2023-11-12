package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface LocalEvent<T> extends Event {
    boolean isPossibleEvent(LocalDate date);
    T applyEvent(LocalDate date);
}