package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.Collections;
import java.util.List;

public enum Appetizer implements Menu {
    MUSHROOM_SOUP("양송이수프", 6000.0),
    TAPAS("타파스", 5500.0),
    CAESAR_SALAD("시저샐러드", 8000.0);

    private final String name;
    private final double price;
    private final List<DiscountEvent> discountEvents;

    Appetizer(String name, double price) {
        this.name = name;
        this.price = price;
        this.discountEvents = Collections.emptyList();
    }

    public String description() {
        return "<애피타이저>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
