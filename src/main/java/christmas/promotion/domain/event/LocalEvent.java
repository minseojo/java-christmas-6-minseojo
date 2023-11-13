package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.VisitDate;

public interface LocalEvent<T> extends Event {
    boolean isPossibleEvent(VisitDate date);
    T applyEvent(VisitDate date);
}