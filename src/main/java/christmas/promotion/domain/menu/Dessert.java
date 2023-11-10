package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.WeekdayDiscount;

import java.util.Collections;
import java.util.List;

public enum Dessert {
    CHOCOLATE_CAKE("초코케이크", 15000.0),
    ICE_CREAM("아이스크림", 5000.0);

    private final String name;
    private final double price;

    Dessert(String name, double price) {
        this.name = name;
        this.price = price;
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
        return Collections.singletonList(new WeekdayDiscount());
    }
}
