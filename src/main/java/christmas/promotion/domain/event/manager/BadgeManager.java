package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.order.Order;

public enum BadgeManager {
    INSTANCE;

    public Badge applyEventBadge(Order order, double discountPrice, double giftPrice) {
        double totalEventPrice = discountPrice + giftPrice;
        return Badge.applyEvent(order.getDate(), totalEventPrice);
    }
}

