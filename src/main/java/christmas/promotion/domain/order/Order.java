package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.MenuItem;

import java.util.HashSet;
import java.util.Set;

public class Order {
    private final Set<OrderItem> orderItems;

    public Order() {
        this.orderItems = new HashSet<>();
    }

    private boolean isMenuExists(String userInput) {
        return orderItems.contains(userInput);
    }

    public void addOrderItem(MenuItem menuItem, int quantity) {
        OrderItem orderItem = new OrderItem(menuItem, quantity);
        orderItems.add(orderItem);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.calculateSubtotal();
        }
        return total;
    }
}
