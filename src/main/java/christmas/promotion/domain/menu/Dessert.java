package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.WeekdayDiscount;

import java.util.Collections;
import java.util.List;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000.0),
    ICE_CREAM("아이스크림", 5000.0);

    private final MenuItem dessert;

    Dessert(String name, double price) {
        this.dessert = new MenuItem(name, price, createDiscountEvents());
    }

    public String description() {
        return "<디저트>";
    }

    @Override
    public String getName() {
        return dessert.getName();
    }

    @Override
    public double getPrice() {
        return dessert.getPrice();
    }

    @Override
    public void applyDiscount() {
        dessert.applyDiscount();
    }

    @Override
    public List<DiscountEvent> getDiscountEvents() {
        return dessert.getDiscountEvents();
    }

    private List<DiscountEvent> createDiscountEvents() {
        return Collections.singletonList(new WeekdayDiscount());
    }
}
