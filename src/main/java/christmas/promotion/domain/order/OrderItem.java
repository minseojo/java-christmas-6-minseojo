package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.Menu;
import christmas.promotion.dto.OrderItemsDto;

public class OrderItem {
    private final Menu menuItem;
    private final int quantity;

    public OrderItem(Menu menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    public void discount() {
        menuItem.applyDiscount();
    }

    public OrderItemsDto toDto() {
        return new OrderItemsDto(menuItem, quantity);
    }
}
