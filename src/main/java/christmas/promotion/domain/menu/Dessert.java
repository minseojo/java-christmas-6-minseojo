package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.WeekdayDiscount;

import java.util.Collections;
import java.util.List;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000.0, Collections.singletonList(WeekdayDiscount.INSTANCE)),
    ICE_CREAM("아이스크림", 5000.0, Collections.singletonList(WeekdayDiscount.INSTANCE));

    private final String name;
    private final double price;
    private final List<DiscountEvent> discountEvents;

    Dessert(String name, double price, List<DiscountEvent> discountEvents) {
        this.name = name;
        this.price = price;
        this.discountEvents = discountEvents;
    }

    public String description() {
        return "<디저트>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<DiscountEvent> getDiscountEvents() {
        return discountEvents;
    }
}