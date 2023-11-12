package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Badge;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.dto.OrderMenusDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    private final List<OrderMenu> orderMenus;
    private final double originalPrice;
    private final LocalDate date;
    private Badge badge;
    private final MenuBoard menuBoard;

    public Order(final Map<String, Integer> order, LocalDate date, final MenuBoard menuBoard) {
        this.menuBoard = menuBoard;
        this.date = date;
        this.orderMenus = List.copyOf(createOrderFromMenuBoard(order));
        this.originalPrice = calculateTotal();
        this.badge = Badge.NONE;
    }

    public boolean isPriceAtLeastTenThousandWon() {
        return originalPrice >= 10000;
    }

    public LocalDate getDate() {
        return date;
    }

    private List<OrderMenu> createOrderFromMenuBoard(Map<String, Integer> order) {
        List<OrderMenu> menus = new ArrayList<>();

        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            Menu menu = menuBoard.findMenu(orderMenu.getKey());
            menus.add(new OrderMenu(menu, orderMenu.getValue()));
        }

        return menus;
    }

    public List<OrderMenu> getOrder() {
        return orderMenus;
    }

    private double calculateTotal() {
        double total = 0.0;
        for (OrderMenu orderMenu : this.orderMenus) {
            total += orderMenu.calculateSubtotal();
        }
        return total;
    }

    public void updateEventBadge(Badge badge) {
        this.badge = badge;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public String getBadgeName() {
        return badge.getName();
    }

    public OrderMenusDto toOrderMenusDto() {
        return new OrderMenusDto(orderMenus);
    }
}
