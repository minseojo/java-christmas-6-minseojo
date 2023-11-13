package christmas.promotion.domain.order;

import java.util.List;

public class OrderMenusValidator {
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final int menuDetailsFactorSize = 2;

    static void validateSize(List<String> menuDetail) {
        if (menuDetail.size() != menuDetailsFactorSize) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    static void validateRange(String number) {
        try {
            int parseNumber = Integer.parseInt(number);
            if (parseNumber < 1) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
