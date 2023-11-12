package christmas.promotion.domain.event;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.ChristmasDiscount;
import christmas.promotion.domain.event.discount.SpecialDiscount;
import christmas.promotion.domain.event.discount.WeekdayDiscount;
import christmas.promotion.domain.event.discount.WeekendDiscount;

import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.event.gift.ChampagneGift;

import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenu;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private static final double EVENT_MINIMUM_PRICE = 10_000.0;

    private final Order order;
    private final double orderOriginalPrice;
    private double discountPrice;
    private double giftPrice;
    private final Map<GlobalEvent, Double> globalEvents; // 크리스마스 디데이 할인, 특별 할인, 샴페인 증정 이벤트
    private final Map<Event, Double> events; // 크리스마스 디데이 할인, 평일 할인, 주말 할인, 특별 할인, 증정 이벤트
    private final Map<Menu, Integer> giftMenus; // 증정 이벤트를 통해 사용자가 얻은 (메뉴, 메뉴 총 수량)

    public EventManager(Order order) {
        this.order = order;
        orderOriginalPrice = order.calculateTotal();
        // 이벤트 출력 순서를 맞추기 위해, LinkedHashMap 고정 (변경 x)
        globalEvents = new LinkedHashMap<>();
        giftMenus = new LinkedHashMap<>();
        events = new LinkedHashMap<>();
        addGlobalEvents();
        addEvents();
        applyEvents();
    }

    private void addGlobalEvents() {
        // 글로벌 이벤트, 글로벌 이벤트는 맵에 넣어두고 할인을 같이 적용 시킨다.
        globalEvents.put(ChristmasDiscount.INSTANCE, 0.0);
        globalEvents.put(SpecialDiscount.INSTANCE, 0.0);

        globalEvents.put(ChampagneGift.INSTANCE, 0.0);
    }

    private void addEvents() {
        // 출력 순서를 유지하기 위해, LinkedHashMap 에 먼저 넣어 놓는다.
        events.put(ChristmasDiscount.INSTANCE, 0.0);
        events.put(WeekdayDiscount.INSTANCE, 0.0);
        events.put(WeekendDiscount.INSTANCE, 0.0);
        events.put(SpecialDiscount.INSTANCE, 0.0);
        events.put(ChampagneGift.INSTANCE, 0.0);
    }

    private void applyEvents() {
        if (order.calculateTotal() < EVENT_MINIMUM_PRICE) {
            return;
        }
        applyGlobalEvents();
        applyMenuDiscountEvents();
    }

    private void applyGlobalEvents() {
        for (GlobalEvent globalEvent : globalEvents.keySet()) {
            if (globalEvent instanceof DiscountEvent) {
                applyDiscountEvent(globalEvent);
            }
            if (globalEvent instanceof GiftEvent) {
                applyGiftEvent(globalEvent);
            }
        }
    }

    private void applyDiscountEvent(GlobalEvent event) {
        if (isPossibleGlobalEvent(event)) {
            double eventSalePrice = event.applyEvent(order.getDate(), orderOriginalPrice);
            discountPrice += eventSalePrice;
            events.put(event, eventSalePrice);
        }
    }

    private void applyGiftEvent(GlobalEvent event) {
        if (isPossibleGlobalEvent(event)) {
            double eventGiftPrice = event.applyEvent(order.getDate(), orderOriginalPrice);
            giftPrice += eventGiftPrice;
            events.put(event, eventGiftPrice);
            addGiftMenu((GiftEvent) event);
        }
    }

    private boolean isPossibleGlobalEvent(GlobalEvent event) {
        return event.isPossibleEvent(order.getDate(), orderOriginalPrice);
    }

    private void addGiftMenu(GiftEvent event) {
        Menu menu = event.getGiftMenu();
        int quantity = event.getGiftQuantity();
        giftMenus.put(menu, quantity);
    }

    private void applyMenuDiscountEvents() {
        for (OrderMenu orderMenu : order.getOrder()) {
            Map<Event, Double> map = orderMenu.applyDiscount(order.getDate());
            for (Event event : map.keySet()) {
                double eventSalePrice = map.getOrDefault(event, 0.0) * orderMenu.getQuantity();
                events.put(event, events.getOrDefault(event, 0.0) + eventSalePrice);
                discountPrice += eventSalePrice;
            }
        }
    }

    public void applyEventBadge() {
        if (Badge.isPossibleEvent(order.getDate())) {
            double totalEventPrice = discountPrice + giftPrice;
            Badge badge = Badge.applyEvent(totalEventPrice);
            order.updateEventBadge(badge);
        }
    }

    public double getOrderOriginalPrice() {
        return orderOriginalPrice;
    }

    public double getSalePrice() {
        return discountPrice;
    }

    public double getGiftPrice() {
        return giftPrice;
    }

    public Map<Event, Double> getEvents() {
        return events;
    }

    public Map<Menu, Integer> getGiftMenus() {
        return giftMenus;
    }
}
