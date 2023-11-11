package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.Event;

import java.time.LocalDate;

public interface GiftEvent extends Event {
    double NO_DISCOUNT = 0;

    @Override
    double applyEvent(LocalDate date, double price);
}
