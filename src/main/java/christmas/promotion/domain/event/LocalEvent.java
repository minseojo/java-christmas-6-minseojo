package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;

public interface LocalEvent<R> extends Event {
    boolean isPossibleEvent(DecemberVisitDate date);
    R applyEvent(DecemberVisitDate date);
}