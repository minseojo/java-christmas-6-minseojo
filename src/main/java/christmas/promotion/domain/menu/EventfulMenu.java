package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.VisitDate;

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

    public Map<Event, Double> applyEvent(VisitDate date, Quantity quantity) {
        Map<Event, Double> eventBenefit = new LinkedHashMap<>();

        Price discountPrice = Price.zero();
        for (LocalEvent localEvent : localEvents) {
            discountPrice = (Price) localEvent.applyEvent(date);
            eventBenefit.put(localEvent, discountPrice.price() * quantity.quantity());
        }

        return eventBenefit;
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
