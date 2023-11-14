package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.GlobalEvent;
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

import static christmas.promotion.domain.event.Event.NON_DISCOUNT_EVENT;
import static christmas.promotion.domain.event.Event.NON_GIFT_EVENT;

public class GlobalEventManager {
    private final Map<GlobalEvent, Double> globalEvents;
    private final Order order;

    public GlobalEventManager(Order order) {
        this.order = order;
        this.globalEvents = new LinkedHashMap<>();
        addGlobalEvents();
    }

    private void addGlobalEvents() {
        globalEvents.put(ChristmasDiscount.INSTANCE, 0.0);
        globalEvents.put(SpecialDiscount.INSTANCE, 0.0);
        globalEvents.put(ChampagneGift.INSTANCE, 0.0);
    }

    public void applyGlobalEvents(Map<Event, Price> eventBenefits, Map<Menu, Integer> eventGifts) {
        for (GlobalEvent globalEvent : globalEvents.keySet()) {
            if (globalEvent instanceof DiscountEvent) {
                applyDiscountEvent(globalEvent, eventBenefits);
            }
            if (globalEvent instanceof GiftEvent) {
                applyGiftEvent(globalEvent, eventBenefits, eventGifts);
            }
        }
    }

    private void applyDiscountEvent(GlobalEvent event, Map<Event, Price> eventBenefits) {
        Price discountPrice = (Price) event.applyEvent(order.getDate(), order.calculateOriginalPrice());
        if (discountPrice.price() > NON_DISCOUNT_EVENT.price()) { // 할인을 한 경우
            Price currentPrice = eventBenefits.get(event);
            eventBenefits.put(event, currentPrice.add(discountPrice.price()));
        }
    }

    private void applyGiftEvent(GlobalEvent event, Map<Event, Price> eventBenefits,
                                Map<Menu, Integer> eventGifts) {

        Price giftPrice = (Price) event.applyEvent(order.getDate(), order.calculateOriginalPrice());
        if (giftPrice.price() > NON_GIFT_EVENT.price()) { // 선물을 증정한 경우
            Price currentPrice = eventBenefits.getOrDefault(event, Price.zero());
            eventBenefits.put(event, currentPrice.add(giftPrice.price()));
            addGiftMenu((GiftEvent) event, eventGifts);
        }
    }

    private void addGiftMenu(GiftEvent event, Map<Menu, Integer> eventGifts) {
        Menu menu = event.getGiftMenu();
        int quantity = event.getGiftQuantity();
        eventGifts.put(menu, quantity);
    }
}

