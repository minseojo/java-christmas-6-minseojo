package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.VisitDate;

public interface GlobalEvent<R, T> extends Event {
    boolean isPossibleEvent(VisitDate date, T value);

    R applyEvent(VisitDate date, T value);
}
