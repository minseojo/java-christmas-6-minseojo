package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;

import java.util.List;

public class OrderManager {
    private static final String menuItemsSplitRegex = ",";
    private static final String menuDetailSplitRegex = "-";
    private static final MenuBoard menuBoard = new MenuBoard();

    private void validate(List<String> menuDetail) {
        if (menuDetail.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void parse(List<OrderItem> orderItems, List<String> menuDetail) {
        try {
            String menuName = menuDetail.get(0);
            int quantity = Integer.parseInt(menuDetail.get(1));
            for (Menu menu : menuBoard.getMenuList()) {
                validate(menuName, menu);
                orderItems.add(new OrderItem(menu, quantity));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
    private void validate(String menuName, Menu menu) {
        if (!menuName.equals(menu.getName())) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }


    private static List<String> splitMenuItems(String input) {
        return List.of(input.split(menuItemsSplitRegex));
    }

    private static List<String> splitMenuDetail(String menuItem) {
        return List.of(menuItem.split(menuDetailSplitRegex));
    }
}
