package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public interface GlobalEvent<R> extends Event {
    boolean isPossibleEvent(DecemberVisitDate date, Price value);

    R applyEvent(DecemberVisitDate date, Price value);
}
