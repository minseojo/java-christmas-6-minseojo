package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface GlobalEvent<T> extends Event {
    boolean isPossibleEvent(LocalDate date, T value);

    T applyEvent(LocalDate date, T value);
}
