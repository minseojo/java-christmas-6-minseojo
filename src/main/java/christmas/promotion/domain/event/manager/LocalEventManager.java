package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;

import java.util.Map;

public enum LocalEventManager {
    INSTANCE;

    public void applyMenuDiscountEvents(Order order, Map<Event, Price> eventBenefits) {
        findAndApplyMenuLocalEvent(order, eventBenefits);
    }

    private static void findAndApplyMenuLocalEvent(Order order, Map<Event, Price> eventBenefits) {
        order.getOrderMenus().forEach(orderMenu ->
                applyMenuLocalEvent(eventBenefits, orderMenu.applyDiscount()));
    }

    private static void applyMenuLocalEvent(Map<Event, Price> eventBenefits, Map<Event, Double> map) {
        map.forEach((event, discountPrice) ->
                eventBenefits.merge(event, Price.of(discountPrice), Price::add));
    }
}

