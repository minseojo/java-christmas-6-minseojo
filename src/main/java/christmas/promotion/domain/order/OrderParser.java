package christmas.promotion.domain.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderParser {
    private static final String menuItemsSplitRegex = ",";
    private static final String menuDetailSplitRegex = "-";

    public static Map<String, Integer> parseOrderMenus(String input) {
        Map<String, Integer> orderItemsDetail = new HashMap<>();

        List<String> menuItems = splitOrderMenus(input);
        for (String menuItem : menuItems) {
            List<String> menuDetail = splitOrderMenuDetail(menuItem);
            parse(orderItemsDetail, menuDetail);
        }

        return orderItemsDetail;
    }

    private static void parse(Map<String, Integer> orderItems, List<String> menuDetail) {
        String menuName = menuDetail.get(0);
        int quantity = Integer.parseInt(menuDetail.get(1));
        orderItems.put(menuName, quantity);
    }

    private static List<String> splitOrderMenus(String input) {
        return List.of(input.split(menuItemsSplitRegex));
    }

    private static List<String> splitOrderMenuDetail(String menuItem) {
        return List.of(menuItem.split(menuDetailSplitRegex));
    }
}
