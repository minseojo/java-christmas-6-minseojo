package christmas.promotion.domain.event.discount;

import java.time.LocalDate;

public interface DiscountEvent {
    double NO_DISCOUNT = 0;

    double applyDiscount(LocalDate date, double price);
}
