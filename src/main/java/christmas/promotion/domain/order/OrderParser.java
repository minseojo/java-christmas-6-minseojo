package christmas.promotion.domain.order;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderParser {
    private static final String menuItemsSplitRegex = ",";
    private static final String menuDetailSplitRegex = "-";

    public static Map<String, Integer> parseOrderDetails(final String input) {
        Map<String, Integer> orderMenuDetail = new LinkedHashMap<>();

        List<String> menuItems = splitOrderMenus(input);
        for (String menuItem : menuItems) {
            List<String> menuDetail = splitOrderMenuDetail(menuItem);
            validateSize(menuDetail);
            parse(orderMenuDetail, menuDetail);
        }

        return orderMenuDetail;
    }
    private static List<String> splitOrderMenus(final String input) {
        return List.of(input.split(menuItemsSplitRegex));
    }

    private static List<String> splitOrderMenuDetail(final String menuItem) {
        return List.of(menuItem.split(menuDetailSplitRegex));
    }

    private static void validateSize(List<String> menuDetail) {
        if (menuDetail.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 유효한 형식이 아닙니다. 다시 입력해 주세요.");
        }
    }

    private static void parse(final Map<String, Integer> orderMenuDetail, final List<String> menuDetail) {
        String menuName = getMenuName(menuDetail);
        int quantity = getQuantity(menuDetail);
        if (orderMenuDetail.containsKey(menuName)) {
            throw  new IllegalArgumentException("[ERROR] 이미 시키신 메뉴 입니다. 다시 입력해 주세요.");
        }
        orderMenuDetail.put(menuName, quantity);
    }

    private static String getMenuName(List<String> menuDetail) {
        return menuDetail.get(0);
    }

    private static int getQuantity(List<String> menuDetail) {
        try {
            return Integer.parseInt(menuDetail.get(1));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 형식이 아닙니다. 다시 입력해 주세요.");
        }
    }
}
