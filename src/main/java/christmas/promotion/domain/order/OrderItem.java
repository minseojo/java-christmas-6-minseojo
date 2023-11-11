package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.MenuItem;

import java.time.LocalDate;
import java.util.Map;

public class OrderItem {
    private final MenuItem menu;
    private final int quantity;

    public OrderItem(MenuItem menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return menu.getPrice() * quantity;
    }

    public MenuItem getMenu() {
        return menu;
    }

    public Map<Event, Double> applyDiscount(LocalDate date) {
        return menu.applyEvent(date);
    }

    public int getQuantity() {
        return quantity;
    }
}
