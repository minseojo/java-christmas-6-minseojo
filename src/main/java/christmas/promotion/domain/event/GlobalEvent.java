package christmas.promotion.domain.event;

import java.time.LocalDate;

public interface GlobalEvent extends Event {
    boolean isPossibleEvent(LocalDate date, double price);
}
