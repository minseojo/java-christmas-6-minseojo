package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenu;
import christmas.promotion.vo.Price;

import java.util.Map;

public enum LocalEventManager {
    INSTANCE;

    public void applyMenuDiscountEvents(Order order, Map<Event, Price> eventBenefits) {
        findAndApplyMenuLocalEvent(order, eventBenefits);
    }

    private static void findAndApplyMenuLocalEvent(Order order, Map<Event, Price> eventBenefits) {
        for (OrderMenu orderMenu : order.getOrderMenus()) {
            Map<Event, Double> menuLocalEvent = orderMenu.applyDiscount();
            applyMenuLocalEvent(eventBenefits, menuLocalEvent);
        }
    }

    private static void applyMenuLocalEvent(Map<Event, Price> eventBenefits, Map<Event, Double> map) {
        for (Map.Entry<Event, Double> event : map.entrySet()) {
            double discountPrice = event.getValue();
            Price currentPrice = eventBenefits.getOrDefault(event, Price.zero());
            eventBenefits.put(event.getKey(), currentPrice.add(discountPrice));
        }
    }
}

