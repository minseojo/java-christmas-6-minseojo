package christmas.promotion.domain.menu;

import java.util.*;

public class MenuBoard {

    private final List<MenuItem> menus;

    public MenuBoard() {
        this.menus = new ArrayList<>();
        initializeMenu();
    }

    public MenuItem findMenu(String name) {
        for (MenuItem menu : menus) {
            if (name.equals(menu.getName())) {
                return menu;
            }
        }

        throw new NoSuchElementException("[ERROR] " + name + " 메뉴를 찾을 수 없습니다. 다시 입력해 주세요.");
    }

    private void initializeMenu() {
        addAppetizer();
        addMainCourse();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        for (Appetizer value : Appetizer.values()) {
            menus.add(MenuItem.createMenuItem(value.getName(), value.getPrice(), value.description()));
        }
    }

    private void addMainCourse() {
        for (MainCourse value : MainCourse.values()) {
            menus.add(MenuItem.createMenuItemWithDiscount(value.getName(), value.getPrice(),
                    value.description(), value.getDiscountEvents()));
        }
    }

    private void addDessert() {
        for (Dessert value : Dessert.values()) {
            menus.add(MenuItem.createMenuItemWithDiscount(value.getName(), value.getPrice(),
                    value.description(), value.getDiscountEvents()));
        }
    }

    private void addBeverage() {
        for (Beverage value : Beverage.values()) {
            menus.add(MenuItem.createMenuItem(value.getName(), value.getPrice(), value.description()));
        }
    }
}
