package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.WeekdayDiscount;
import christmas.promotion.domain.event.discount.WeekendDiscount;

import java.util.*;

public class MenuBoard {

    private final List<MenuItem> menus;

    public MenuBoard() {
        this.menus = new ArrayList<>();
        initializeMenu();
    }

    public Menu findMenu(String name) {
        for (Menu menu : menus) {
            if (name.equals(menu.getName())) {
                return menu;
            }
        }

        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private void initializeMenu() {
        addAppetizer();
        addMainCourse();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        for (Appetizer value : Appetizer.values()) {
            menus.add(MenuItem.createMenuItem(value));
        }
    }

    private void addMainCourse() {
        for (MainCourse value : MainCourse.values()) {
            menus.add(MenuItem.createMenuItemWithDiscount(value, Collections.singletonList(WeekendDiscount.INSTANCE)));
        }
    }

    private void addDessert() {
        for (Dessert value : Dessert.values()) {
            menus.add(MenuItem.createMenuItemWithDiscount(value, Collections.singletonList(WeekdayDiscount.INSTANCE)));
        }
    }

    private void addBeverage() {
        for (Beverage value : Beverage.values()) {
            menus.add(MenuItem.createMenuItem(value));
        }
    }

    public List<MenuItem> getMenus() {
        return menus;
    }
}
