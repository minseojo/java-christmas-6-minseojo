package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;

import java.time.LocalDate;

public interface DiscountEvent extends Event {
    double NO_DISCOUNT = 0;

    @Override
    double applyEvent(LocalDate date, double price);
}
