package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.Collections;
import java.util.List;

public enum Beverage implements Menu {
    ZERO_COLA("제로콜라", 3000.0),
    RED_WINE("레드와인", 60000.0),
    CHAMPAGNE("샴페인", 25000.0);

    private final MenuItem beverage;

    Beverage(String name, double price) {
        this.beverage = new MenuItem(name, price, createDiscountEvents());
    }

    @Override
    public String description() {
        return "<음료>";
    }

    @Override
    public String getName() {
        return beverage.getName();
    }

    @Override
    public double getPrice() {
        return beverage.getPrice();
    }

    @Override
    public void applyDiscount() {
        beverage.applyDiscount();
    }

    @Override
    public List<DiscountEvent> getDiscountEvents() {
        return beverage.getDiscountEvents();
    }

    private List<DiscountEvent> createDiscountEvents() {
        return Collections.emptyList();
    }
}