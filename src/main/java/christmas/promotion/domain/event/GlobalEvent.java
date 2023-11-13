package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface GlobalEvent<R, T> extends Event {
    boolean isPossibleEvent(LocalDate date, T value);

    R applyEvent(LocalDate date, T value);
}
