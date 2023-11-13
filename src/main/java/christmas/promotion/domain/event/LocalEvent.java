package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;

public interface LocalEvent<T> extends Event {
    boolean isPossibleEvent(DecemberVisitDate date);
    T applyEvent(DecemberVisitDate date);
}