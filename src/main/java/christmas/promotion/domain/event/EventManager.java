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
    private final Map<Event, Double> commonEvents;
    private final Map<Event, Double> events;

    public EventManager(Order order) {
        this.order = order;
        commonEvents = new LinkedHashMap<>();
        events = new LinkedHashMap<>(); // 맵에 객체를 넣은 순서 유지 (크리스마스 디데이 할인, 특별 할인, 증정 이벤트)
        addCommonEvents();
        applyEvent();
    }

    private void addCommonEvents() {
        commonEvents.put(new ChristmasDiscount(), 0.0);
        commonEvents.put(new SpecialDiscount(), 0.0);
        commonEvents.put(new ChampagneGift(), 0.0);
    }

    public void print() {
        for (Event event : events.keySet()) {
            if (event.getClass() == ChristmasDiscount.class) {
                System.out.print("크리스마 디데이 할인: ");
            }
            if (event.getClass().equals(WeekdayDiscount.class)) {
                System.out.print("평일 할인:" );
            }
            if (event.getClass().equals(WeekendDiscount.class)) {
                System.out.print("주말 할인: ");
            }

            if (event.getClass().equals(SpecialDiscount.class)) {
                System.out.print("특별 할인:");
            }
            if (event.getClass().equals(ChampagneGift.class)) {
                System.out.print("증정 이벤트: ");
            }
            System.out.println(events.getOrDefault(event, 0.0));
        }

        System.out.println(order.getBadgeName());
    }

    private void applyEvent() {
        if (!order.isPriceAtLeastTenThousandWon()) {
            return;
        }

        for (Event event : commonEvents.keySet()) {
            if (event instanceof DiscountEvent) {
                double eventSalePrice = ((DiscountEvent) event).applyDiscount(order.getDate(), order.getOriginalPrice());
                salePrice += eventSalePrice;
                events.put(event, eventSalePrice);
            }

            if (event instanceof GiftEvent) {
                double eventSalePrice = ((GiftEvent) event).applyGift(order.getDate(), order.getOriginalPrice());
                salePrice += eventSalePrice;
                events.put(event, eventSalePrice);
            }
        }

        for (OrderItem orderItem : order.getOrder()) {
            Map<Event, Double> map = orderItem.applyDiscount(order.getDate());
            for (Event event : map.keySet()) {
                double eventSalePrice = map.getOrDefault(event, 0.0) * orderItem.getQuantity();
                events.put(event, events.getOrDefault(event, 0.0) + eventSalePrice);
                salePrice += eventSalePrice;
            }
        }

    }

    public void applyEventBadge() {
        Badge badge = Badge.grantBadgeOnExceedingAmountThreshold(salePrice);
        order.updateEventBadge(badge);
    }

    public double getSalePrice() {
        return salePrice;
    }
}
