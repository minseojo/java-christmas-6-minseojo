package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.menu.MenuItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private final List<OrderItem> order;
    private final double originalPrice;
    private final LocalDate date;
    private final MenuBoard menuBoard;

    public Order(final Map<String, Integer> orderMenus, LocalDate date, final MenuBoard menuBoard) {
        this.menuBoard = menuBoard;
        this.date = date;
        this.order = List.copyOf(createOrderFromMenuBoard(orderMenus));
        this.originalPrice = calculateTotal();
    }

    public LocalDate getDate() {
        return date;
    }

    private List<OrderItem> createOrderFromMenuBoard(Map<String, Integer> orderMenus) {
        List<OrderItem> menus = new ArrayList<>();

        for (Map.Entry<String, Integer> orderMenu : orderMenus.entrySet()) {
            MenuItem menuItem = menuBoard.findMenu(orderMenu.getKey());
            menus.add(new OrderItem(menuItem, orderMenu.getValue()));
        }

        return menus;
    }

    public List<OrderItem> getOrder() {
        return order;
    }

    private double calculateTotal() {
        double total = 0.0;
        for (OrderItem orderItem : order) {
            total += orderItem.calculateSubtotal();
        }
        return total;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }
}
