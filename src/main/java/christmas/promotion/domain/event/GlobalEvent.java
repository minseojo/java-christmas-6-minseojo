package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;

public interface GlobalEvent<R, T> extends Event {
    boolean isPossibleEvent(DecemberVisitDate date, T value);

    R applyEvent(DecemberVisitDate date, T value);
}
