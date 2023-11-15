package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;

import java.util.HashMap;
import java.util.Map;

public enum LocalEventManager {
    INSTANCE;

    public Map<Event, Price> applyMenuDiscountEvents(Order order) {
        return findAndApplyMenuLocalEvent(order);
    }

    private static Map<Event, Price>  findAndApplyMenuLocalEvent(Order order) {
        Map<Event, Price> evnetBenefits = new HashMap<>();

        order.getOrderMenus().forEach(orderMenu ->
                applyMenuLocalEvent(evnetBenefits, orderMenu.applyDiscount()));

        return evnetBenefits;
    }

    private static void applyMenuLocalEvent(Map<Event, Price> eventBenefits, Map<Event, Double> menuLocalEvents) {
        menuLocalEvents.forEach((event, discountPrice) ->
                eventBenefits.merge(event, Price.of(discountPrice), Price::add));
    }
}

