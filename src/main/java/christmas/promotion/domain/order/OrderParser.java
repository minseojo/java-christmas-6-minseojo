package christmas.promotion.domain.order;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderParser {
    private static final String menuItemsSplitRegex = ",";
    private static final String menuDetailSplitRegex = "-";
    private static final int menuDetailNameIndex = 0;
    private static final int menuDetailQuantityIndex = 1;


    public static Map<String, Integer> parse(final String input) {
        Map<String, Integer> orderMenuDetails = new LinkedHashMap<>();

        List<String> menuItems = splitOrderMenus(input);
        for (String menuItem : menuItems) {
            List<String> menuDetail = splitOrderMenuDetail(menuItem);
            OrderValidator.validateSize(menuDetail);
            OrderValidator.validateInteger(menuDetail.get(menuDetailQuantityIndex));
            addMenuDetail(orderMenuDetails, menuDetail);
        }

        return orderMenuDetails;
    }

    private static List<String> splitOrderMenus(final String input) {
        return List.of(input.split(menuItemsSplitRegex));
    }

    private static List<String> splitOrderMenuDetail(final String menuItem) {
        return List.of(menuItem.split(menuDetailSplitRegex));
    }

    private static void addMenuDetail(Map<String, Integer> orderMenuDetails, List<String> menuDetail) {
        String menuName = getMenuName(menuDetail);
        int quantity = getQuantity(menuDetail);
        orderMenuDetails.put(menuName, quantity);
    }

    private static String getMenuName(List<String> menuDetail) {
        return menuDetail.get(menuDetailNameIndex);
    }

    private static int getQuantity(List<String> menuDetail) {
        return Integer.parseInt(menuDetail.get(menuDetailQuantityIndex));
    }
}
