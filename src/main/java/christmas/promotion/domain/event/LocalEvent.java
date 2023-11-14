package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public interface LocalEvent extends Event {
    boolean isPossibleEvent(DecemberVisitDate date);
    Price applyEvent(DecemberVisitDate date);
}