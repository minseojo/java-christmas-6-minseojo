package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuItem implements Menu {
    Menu menu;
    private final List<LocalEvent> localEvents;

    private MenuItem(Menu menu, List<LocalEvent> localEvents) {
        this.menu = menu;
        this.localEvents = localEvents;
    }

    public static MenuItem createMenuItem(Menu menu) {
        return new MenuItem(menu, Collections.emptyList());
    }

    public static MenuItem createMenuItemWithDiscount(Menu menu, List<LocalEvent> localEvents) {
        return new MenuItem(menu, localEvents);
    }

    public Map<Event, Double> applyEvent(LocalDate date) {
        double salePrice = 0;
        Map<Event, Double> eventMap = new LinkedHashMap<>();

        for (LocalEvent localEvent : localEvents) {
            System.out.println(localEvent.getEventName());
            System.out.println(isPossibleEvent(localEvent, date));
            if (isPossibleEvent(localEvent, date)) {
                salePrice += localEvent.applyEvent(date, salePrice);
                eventMap.put(localEvent, salePrice);
            }
        }

        return eventMap;
    }

    private boolean isPossibleEvent(LocalEvent event, LocalDate date) {
        return event.isPossibleEvent(date);
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
}
