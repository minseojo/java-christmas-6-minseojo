package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.discount.DiscountEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuItem implements Menu {
    Menu menu;
    private final List<DiscountEvent> discountEvents;

    private MenuItem(Menu menu, List<DiscountEvent> discountEvents) {
        this.menu = menu;
        this.discountEvents = discountEvents;
    }

    public static MenuItem createMenuItem(Menu menu) {
        return new MenuItem(menu, Collections.emptyList());
    }

    public static MenuItem createMenuItemWithDiscount(Menu menu, List<DiscountEvent> discountEvents) {
        return new MenuItem(menu, discountEvents);
    }

    @Override
    public String description() {
        return "메뉴";
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String getName() {
        return menu.getName();
    }

    @Override
    public double getPrice() {
        return menu.getPrice();
    }

    public Map<Event, Double> applyEvent(LocalDate date) {
        double salePrice = 0;
        Map<Event, Double> eventMap = new LinkedHashMap<>();

        for (DiscountEvent discountEvent : discountEvents) {
            if (isEventPeriod(discountEvent, date)) {
                salePrice += discountEvent.applyEvent(date, salePrice);
                eventMap.put(discountEvent, salePrice);
            }
        }

        return eventMap;
    }

    private boolean isEventPeriod(DiscountEvent event, LocalDate date) {
        return event.isBetweenDates(date);
    }
}
