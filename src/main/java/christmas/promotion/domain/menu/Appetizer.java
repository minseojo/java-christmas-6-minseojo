package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.Collections;
import java.util.List;

public enum Appetizer implements Menu{
    MUSHROOM_SOUP("양송이수프", 6000.0),
    TAPAS("타파스", 5500.0),
    CAESAR_SALAD("시저샐러드", 8000.0);

    private final MenuItem appetizer;

    Appetizer(String name, double price) {
        this.appetizer = new MenuItem(name, price, createDiscountEvents());
    }

    @Override
    public String description() {
        return "<애피타이저>";
    }

    @Override
    public String getName() {
        return appetizer.getName();
    }

    @Override
    public double getPrice() {
        return appetizer.getPrice();
    }

    @Override
    public void applyDiscount() {
        appetizer.applyDiscount();
    }

    @Override
    public List<DiscountEvent> getDiscountEvents() {
        return appetizer.getDiscountEvents();
    }

    private List<DiscountEvent> createDiscountEvents() {
        return Collections.emptyList();
    }
}
