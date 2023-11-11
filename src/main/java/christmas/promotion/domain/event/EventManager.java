package christmas.promotion.domain.event;

import christmas.promotion.domain.event.discount.*;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {

    private final Order order;
    private double salePrice;
    private double giftPrice;
    private final Map<Event, Double> globalEvents; // 크리스마스 디데이 할인, 특별 할인, 증정 이벤트
    private final Map<Event, Double> events;

    public EventManager(Order order) {
        this.order = order;
        globalEvents = new LinkedHashMap<>();
        events = new LinkedHashMap<>();
        addGlobalEvents();
        applyEvents();
    }

    private void addGlobalEvents() {
        globalEvents.put(ChristmasDiscount.INSTANCE, 0.0);
        globalEvents.put(SpecialDiscount.INSTANCE, 0.0);
        globalEvents.put(ChampagneGift.INSTANCE, 0.0);
    }

    private void applyEvents() {
        if (!order.isPriceAtLeastTenThousandWon()) {
            return;
        }
        applyGlobalEvents();
        applyMenuEvents();
    }

    private void applyGlobalEvents() {
        for (Event event : globalEvents.keySet()) {
            applyDiscountEvent(event);
            applyGiftEvent(event);
        }
    }

    private void applyMenuEvents() {
        for (OrderItem orderItem : order.getOrder()) {
            Map<Event, Double> map = orderItem.applyDiscount(order.getDate());
            for (Event event : map.keySet()) {
                double eventSalePrice = map.getOrDefault(event, 0.0) * orderItem.getQuantity();
                events.put(event, events.getOrDefault(event, 0.0) + eventSalePrice);
                salePrice += eventSalePrice;
            }
        }
    }

    private void applyDiscountEvent(Event event) {
        if (event instanceof DiscountEvent) {
            double eventSalePrice = event.applyEvent(order.getDate(), order.getOriginalPrice());
            salePrice += eventSalePrice;
            events.put(event, eventSalePrice);
        }
    }

    private void applyGiftEvent(Event event) {
        if (event instanceof GiftEvent) {
            double eventGiftPrice = event.applyEvent(order.getDate(), order.getOriginalPrice());
            giftPrice += eventGiftPrice;
            events.put(event, eventGiftPrice);
        }
    }

    public void applyEventBadge() {
        Badge badge = Badge.grantBadgeOnExceedingAmountThreshold(salePrice + giftPrice);
        order.updateEventBadge(badge);
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getGiftPrice() {
        return giftPrice;
    }
}
