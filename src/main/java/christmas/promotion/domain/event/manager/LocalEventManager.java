package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenu;
import christmas.promotion.vo.Price;

import java.util.Map;

public class LocalEventManager {
    public void applyMenuDiscountEvents(Order order, Map<Event, Price> eventBenefits) {
        for (OrderMenu orderMenu : order.getOrder()) {
            Map<Event, Double> map = orderMenu.applyDiscount(order.getDate());

            for (Map.Entry<Event, Double> event : map.entrySet()) {
                double discountPrice = event.getValue();
                Price currentPrice = eventBenefits.getOrDefault(event, Price.zero());
                eventBenefits.put(event.getKey(), currentPrice.add(discountPrice));
            }
        }
    }
}

