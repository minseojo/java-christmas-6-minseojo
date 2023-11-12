package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuItem;

import java.time.LocalDate;
import java.util.Map;

public class OrderMenu {
    private final MenuItem menu;
    private final int quantity;

    public OrderMenu(Menu menu, int quantity) {
        this.menu = (MenuItem) menu;
        this.quantity = quantity;
    }

    public Map<Event, Double> applyDiscount(LocalDate date) {
        return menu.applyEvent(date);
    }

    public double calculateSubtotal() {
        return menu.getPrice() * quantity;
    }

    public Menu getMenu() {
        return menu.getMenu();
    }

    public int getQuantity() {
        return quantity;
    }
}
