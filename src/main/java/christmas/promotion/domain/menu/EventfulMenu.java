package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventfulMenu {
    private final Menu menu;
    private final List<LocalEvent> localEvents;

    private EventfulMenu(Menu menu, List<LocalEvent> localEvents) {
        this.menu = menu;
        this.localEvents = localEvents;
    }

    public static EventfulMenu createMenuItem(Menu menu) {
        return new EventfulMenu(menu, Collections.emptyList());
    }

    public static EventfulMenu createMenuItemWithDiscount(Menu menu, List<LocalEvent> localEvents) {
        return new EventfulMenu(menu, localEvents);
    }

    public Map<Event, Double> applyEvent(LocalDate date) {
        double salePrice = 0;
        Map<Event, Double> eventMap = new LinkedHashMap<>();

        for (LocalEvent localEvent : localEvents) {
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

    public String description() {
        return "메뉴";
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return menu.getName();
    }

    public double getPrice() {
        return menu.getPrice();
    }
}
