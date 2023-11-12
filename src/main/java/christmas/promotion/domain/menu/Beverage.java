package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.Collections;
import java.util.List;

public enum Beverage implements Menu {
    ZERO_COLA("제로콜라", 3000.0),
    RED_WINE("레드와인", 60000.0),
    CHAMPAGNE("샴페인", 25000.0);

    private final String name;
    private final double price;
    private final List<DiscountEvent> discountEvents;

    Beverage(String name, double price) {
        this.name = name;
        this.price = price;
        this.discountEvents = Collections.emptyList();
    }

    public String description() {
        return "<음료>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
