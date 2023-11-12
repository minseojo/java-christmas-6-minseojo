package christmas.promotion.domain.event;


import christmas.promotion.domain.event.discount.ChristmasDiscount;
import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.SpecialDiscount;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalEventManager {
    private final Map<GlobalEvent, Double> globalEvents;

    public GlobalEventManager() {
        this.globalEvents = new LinkedHashMap<>();
        addGlobalEvents();
    }

    private void addGlobalEvents() {
        globalEvents.put(ChristmasDiscount.INSTANCE, 0.0);
        globalEvents.put(SpecialDiscount.INSTANCE, 0.0);
        globalEvents.put(ChampagneGift.INSTANCE, 0.0);
    }

    public void applyGlobalEvents(Order order, Map<Event, Price> eventBenefits, Map<Menu, Integer> eventGifts) {
        for (GlobalEvent globalEvent : globalEvents.keySet()) {
            if (globalEvent instanceof DiscountEvent) {
                applyDiscountEvent(order, globalEvent, eventBenefits);
            }
            if (globalEvent instanceof GiftEvent) {
                applyGiftEvent(order, globalEvent, eventBenefits, eventGifts);
            }
        }
    }

    private void applyDiscountEvent(Order order, GlobalEvent event, Map<Event, Price> eventBenefits) {
        if (isPossibleGlobalEvent(order, event)) {
            double discountPrice = event.applyEvent(order.getDate(), order.calculateTotal().price());
            Price currentPrice = eventBenefits.getOrDefault(event, new Price(0.0));
            eventBenefits.put(event, currentPrice.add(discountPrice));
        }
    }

    private void applyGiftEvent(Order order, GlobalEvent event, Map<Event, Price> eventBenefits,
                                Map<Menu, Integer> eventGifts) {

        if (isPossibleGlobalEvent(order, event)) {
            double giftPrice = event.applyEvent(order.getDate(), order.calculateTotal().price());
            Price currentPrice = eventBenefits.getOrDefault(event, new Price(0.0));
            eventBenefits.put(event, currentPrice.add(giftPrice));
            addGiftMenu((GiftEvent) event, eventGifts);
        }
    }

    private boolean isPossibleGlobalEvent(Order order, GlobalEvent event) {
        return event.isPossibleEvent(order.getDate(), order.calculateTotal().price());
    }

    private void addGiftMenu(GiftEvent event, Map<Menu, Integer> eventGifts) {
        Menu menu = event.getGiftMenu();
        int quantity = event.getGiftQuantity();
        eventGifts.put(menu, quantity);
    }

}

