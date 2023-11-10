package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.List;

public interface Menu {
    String description();
    String getName();
    double getPrice();
    List<DiscountEvent> getDiscountEvents();
    void applyDiscount();
}
