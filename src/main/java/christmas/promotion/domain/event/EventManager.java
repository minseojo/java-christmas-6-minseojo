package christmas.promotion.domain.event;

import christmas.promotion.domain.event.discount.*;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final Order order;
    private double salePrice;
    private final List<DiscountEvent> discountEvents;
    private final List<GiftEvent> giftEvents;

    public EventManager(Order order) {
        this.order = order;
        this.discountEvents = new ArrayList<>();
        this.giftEvents = new ArrayList<>();
        addEvents();
        applyEvent();
    }

    private void addEvents() {
        discountEvents.add(new ChristmasDiscount());
        discountEvents.add(new SpecialDiscount());

        giftEvents.add(new ChampagneGift());
    }


    private void applyEvent() {
        if (order.getOriginalPrice() < 10_000) {
            return;
        }

        for (DiscountEvent event : discountEvents) {
            salePrice += event.applyDiscount(order.getDate(), order.getOriginalPrice());
        }

        for (GiftEvent event : giftEvents) {
            salePrice += event.applyGift(order.getDate(), order.getOriginalPrice());
        }

        for (OrderItem orderItem : order.getOrder()) {
            salePrice += orderItem.applyDiscount(order.getDate()) * orderItem.getQuantity();
        }
    }

    public double getSalePrice() {
        return salePrice;
    }
}
