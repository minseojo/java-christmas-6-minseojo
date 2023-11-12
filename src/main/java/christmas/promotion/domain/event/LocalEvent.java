package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface LocalEvent<T> extends Event<T> {
    boolean isPossibleEvent(LocalDate date);
}