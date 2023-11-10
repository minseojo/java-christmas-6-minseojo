package christmas.promotion.dto;

import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.OrderItem;

public class OrderItemsDto {
    private final OrderItem orderItem;

    public OrderItemsDto(Menu menuItem, int quantity) {
        this.orderItem = new OrderItem(menuItem, quantity);
    }
}
