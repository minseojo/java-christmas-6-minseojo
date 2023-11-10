package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.util.List;

public class MenuItem implements Menu {
    private final String name;
    private final double price;
    private final List<DiscountEvent> discountEvents;

    public MenuItem(String name, double price, List<DiscountEvent> discountEvents) {
        this.name = name;
        this.price = price;
        this.discountEvents = discountEvents;
    }

    @Override
    public String description() {
        return "<메뉴>";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public List<DiscountEvent> getDiscountEvents() {
        return discountEvents;
    }

    @Override
    public void applyDiscount() {
        for (DiscountEvent discountEvent : discountEvents) {
            discountEvent.applyDiscount(price);
        }
    }
}