package christmas.promotion.domain.event.gift;

import java.time.LocalDate;

public interface GiftEvent {
    double NO_DISCOUNT = 0;

    double applyGift(LocalDate date, double price);
}
