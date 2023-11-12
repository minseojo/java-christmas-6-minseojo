package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.discount.DiscountEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuItem implements Menu {
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

    @Override
    public String description() {
        return "메뉴";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public Map<Event, Double> applyEvent(LocalDate date) {
        double salePrice = 0;
        Map<Event, Double> eventMap = new LinkedHashMap<>();

        for (DiscountEvent discountEvent : discountEvents) {
            if (isEventApplicable(discountEvent, date)) {
                salePrice += discountEvent.applyEvent(date, salePrice);
                eventMap.put(discountEvent, salePrice);
            }
        }

        return eventMap;
    }

    private boolean isEventApplicable(DiscountEvent event, LocalDate date) {
        return event.isBetweenDates(date);
    }
}
