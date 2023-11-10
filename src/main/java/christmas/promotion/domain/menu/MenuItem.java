package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MenuItem {
    private final String name;
    private final double price;
    private final String category;
    private final List<DiscountEvent> discountEvents;

    private MenuItem(String name, double price, String category, List<DiscountEvent> discountEvents) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.discountEvents = discountEvents;
    }

    public static MenuItem createMenuItem(String name, double price, String category) {
        return new MenuItem(name, price, category, Collections.emptyList());
    }

    public static MenuItem createMenuItemWithDiscount(String name, double price, String category, List<DiscountEvent> discountEvents) {
        return new MenuItem(name, price, category, discountEvents);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double applyDiscount(LocalDate date) {
        double salePrice = 0;
        for (DiscountEvent discountEvent : discountEvents) {
            salePrice += discountEvent.applyDiscount(date, salePrice);
        }
        System.out.println(salePrice);
        return salePrice;
    }
}
