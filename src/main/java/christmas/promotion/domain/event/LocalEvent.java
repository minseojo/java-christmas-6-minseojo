package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface LocalEvent extends Event {
    boolean isPossibleEvent(LocalDate date);
}
