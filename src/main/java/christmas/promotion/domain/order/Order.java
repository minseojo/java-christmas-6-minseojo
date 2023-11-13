package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private static final int ORDER_MENU_MAX_SIZE = 20;

    private final List<OrderMenu> orderMenus;
    private final Price orderPrice;
    private final LocalDate date;
    private final MenuBoard menuBoard;

    public Order(final Map<String, Integer> order, LocalDate date, final MenuBoard menuBoard) {
        this.menuBoard = menuBoard; // createOrderFromMenuBoard 하기전에 메뉴판을 동적으로 초기화 해줘야함.
        this.orderMenus = List.copyOf(createOrderFromMenuBoard(order));
        validate();
        this.orderPrice = Price.of(calculateTotal().price());
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    private List<OrderMenu> createOrderFromMenuBoard(Map<String, Integer> order) {
        List<OrderMenu> menus = new ArrayList<>();
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            EventfulMenu menu = menuBoard.findMenu(orderMenu.getKey());
            menus.add(new OrderMenu(menu, new Quantity(orderMenu.getValue())));
        }
        return menus;
    }

    public List<OrderMenu> getOrder() {
        return orderMenus;
    }

    private Price calculateTotal() {
        double total = 0.0;
        for (OrderMenu orderMenu : this.orderMenus) {
            total += orderMenu.calculateSubtotal();
        }
        return Price.of(total);
    }

    public Price getOrderPrice() {
        return orderPrice;
    }

    private void validate() {
        validateMaxSize();
        validateDuplicate();
        validateOnlyBeverage();
    }

    private void validateMaxSize() {
        int size = 0;

        for (OrderMenu orderMenu : orderMenus) {
            size += orderMenu.getQuantity().quantity();
        }

        if (size > ORDER_MENU_MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicate() {
        int initialSize = orderMenus.size();

        Set<OrderMenu> uniqueMenu = new HashSet<>(orderMenus);
        int uniqueMenuSize = uniqueMenu.size();

        if (initialSize != uniqueMenuSize) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOnlyBeverage() {
        for (OrderMenu orderMenu : orderMenus) {
            if (isNotBeverage(orderMenu.getMenu())) {
                return;
            }
        }

        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private boolean isNotBeverage(Menu menu) {
        return !(menu instanceof Beverage);
    }
}
