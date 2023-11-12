package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.order.Order;

public class BadgeManager {
    public void applyEventBadge(Order order, double discountPrice, double giftPrice) {
        if (Badge.isPossibleEvent(order.getDate())) {
            double totalEventPrice = discountPrice + giftPrice;
            Badge badge = Badge.applyEvent(totalEventPrice);
            order.updateEventBadge(badge);
        }
    }
}

