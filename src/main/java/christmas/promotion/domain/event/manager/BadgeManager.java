package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public enum BadgeManager {
    INSTANCE;

    public Badge applyEventBadge(Order order, double discountPrice, double giftPrice) {
        Price totalEventPrice = Price.of(discountPrice + giftPrice);

        for (Badge badge : Badge.values()) {
            DecemberVisitDate orderDate = order.getDate();
            if (badge.isPossibleEvent(orderDate, totalEventPrice)) {
                return badge.applyEvent(orderDate, totalEventPrice);
            }
        }

        return Badge.NONE;
    }
}

