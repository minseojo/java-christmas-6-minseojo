package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.exception.OrderMenuException;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.util.*;

public class Order {
    private static final int ORDER_MENU_MAX_SIZE = 20;

    private final List<OrderMenu> orderMenus;
    private final DecemberVisitDate date;
    private final MenuBoard menuBoard;

    public Order(final Map<String, Integer> order, final DecemberVisitDate decemberVisitDate, final MenuBoard menuBoard) {
        this.menuBoard = menuBoard; // createOrderFromMenuBoard 하기 전, 메뉴판을 동적으로 초기화 해줘야함. 아니면 null
        this.date = decemberVisitDate; // createOrderFromMenuBoard 하기 전, 날짜 먼저 동적으로 최기화 해줘야함. 아니면 null
        this.orderMenus = List.copyOf(createOrderFromMenuBoard(order));
        validate();
    }

    private List<OrderMenu> createOrderFromMenuBoard(Map<String, Integer> order) {
        List<OrderMenu> menus = new ArrayList<>();
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            EventfulMenu menu = menuBoard.findMenu(orderMenu.getKey());
            menus.add(new OrderMenu(menu, new Quantity(orderMenu.getValue()), this.date));
        }

        return menus;
    }

    public Price calculateOriginalPrice() {
        double total = 0.0;
        for (OrderMenu orderMenu : this.orderMenus) {
            total += orderMenu.calculateSubtotal();
        }
        return Price.of(total);
    }

    private void validate() {
        validateMenuMaxSize();
        validateMenuDuplicate();
        validateMenuOnlyBeverage();
    }

    private void validateMenuMaxSize() {
        int size = 0;
        for (OrderMenu orderMenu : orderMenus) {
            size += orderMenu.getQuantity().quantity();
        }
        if (size > ORDER_MENU_MAX_SIZE) {
            throw new OrderMenuException();
        }
    }

    private void validateMenuDuplicate() {
        int initialSize = orderMenus.size();

        Set<OrderMenu> uniqueMenu = new HashSet<>(orderMenus);
        int uniqueMenuSize = uniqueMenu.size();

        if (initialSize != uniqueMenuSize) {
            throw new OrderMenuException();
        }
    }

    private void validateMenuOnlyBeverage() {
        for (OrderMenu orderMenu : orderMenus) {
            if (isNotBeverage(orderMenu.getMenu())) {
                return;
            }
        }

        throw new OrderMenuException();
    }

    private boolean isNotBeverage(Menu menu) {
        return !(menu instanceof Beverage);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public DecemberVisitDate getDate() {
        return date;
    }
}
