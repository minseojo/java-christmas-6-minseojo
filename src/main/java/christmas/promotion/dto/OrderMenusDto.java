package christmas.promotion.dto;

import christmas.promotion.domain.order.OrderMenu;

import java.util.List;

public class OrderMenusDto {
    private final List<OrderMenu> orderMenus;

    public OrderMenusDto(List<OrderMenu> orderMenus) {
        this.orderMenus = List.copyOf(orderMenus);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }
}
