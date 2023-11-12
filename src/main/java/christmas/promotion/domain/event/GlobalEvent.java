package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface GlobalEvent<T> extends Event<T> {
    boolean isPossibleEvent(LocalDate date, T value);
}
