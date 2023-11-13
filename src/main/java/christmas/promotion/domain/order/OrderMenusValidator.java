package christmas.promotion.domain.order;

import java.util.List;

public class OrderMenusValidator {
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final int menuDetailsFactorSize = 2;
    private static final int menusMinimumSize = 1;

    public static void validateMenuDetailsCommaStartOrEnd(String menuDetails) {
        if (menuDetails.startsWith(",") || menuDetails.endsWith(",")) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateMenuDtailSize(List<String> menuDetail) {
        if (menuDetail.size() != menuDetailsFactorSize) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateMenuNameBlank(String menuName) {
        if (menuName.isBlank() || menuName.contains(" ")) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateMenuSizeInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateMenuMinimumSize(String number) {
        try {
            int parseNumber = Integer.parseInt(number);
            if (parseNumber < menusMinimumSize) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
