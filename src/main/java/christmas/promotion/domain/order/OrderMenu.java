package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.vo.Quantity;

import java.time.LocalDate;
import java.util.Map;

public class OrderMenu {
    private final EventfulMenu menu;
    private final Quantity quantity;

    public OrderMenu(EventfulMenu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Map<Event, Double> applyDiscount(LocalDate date) {
        return menu.applyEvent(date, quantity);
    }

    public double calculateSubtotal() {
        return menu.getPrice() * quantity.quantity();
    }

    public Menu getMenu() {
        return menu.getMenu();
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
