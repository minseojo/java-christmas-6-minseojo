package christmas.promotion.domain.order;

import christmas.promotion.exception.OrderMenuException;

import java.util.List;
import java.util.Map;

public class OrderMenusValidator {
    private static final int menuDetailsFactorSize = 2;
    private static final int menusMinimumSize = 1;

    public static void validateMenuDetailsCommaStartOrEnd(String menuDetails) {
        if (menuDetails.startsWith(",") || menuDetails.endsWith(",")) {
            throw new OrderMenuException();
        }
    }

    static void validateMenuDtailSize(List<String> menuDetail) {
        if (menuDetail.size() != menuDetailsFactorSize) {
            throw new OrderMenuException();
        }
    }

    static void validateMenuNameBlank(String menuName) {
        if (menuName.isBlank() || menuName.contains(" ")) {
            throw new OrderMenuException();
        }
    }

    static void validateMenuSizeInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException exception) {
            throw new OrderMenuException();
        }
    }

    static void validateMenuMinimumSize(String number) {
        try {
            int parseNumber = Integer.parseInt(number);
            if (parseNumber < menusMinimumSize) {
                throw new OrderMenuException();
            }
        } catch (IllegalArgumentException exception) {
            throw new OrderMenuException();
        }
    }

    static void validateMenuDuplicate(Map<String, Integer> orderMenuDetails, String orderMenuName) {
        for (String orderedMenuName : orderMenuDetails.keySet()) {
            if (orderedMenuName.equals(orderMenuName)) {
                throw new OrderMenuException();
            }
        }

    }
}
