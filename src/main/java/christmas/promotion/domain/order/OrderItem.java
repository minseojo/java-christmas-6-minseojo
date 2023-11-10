package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.MenuItem;

import java.time.LocalDate;

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

    public double applyDiscount(LocalDate date) {
        return menu.applyDiscount(date);
    }

    public int getQuantity() {
        return quantity;
    }
}
