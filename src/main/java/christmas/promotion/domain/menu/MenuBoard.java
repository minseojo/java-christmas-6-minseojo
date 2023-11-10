package christmas.promotion.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuBoard {
    private final List<Menu> menuList;

    public MenuBoard() {
        this.menuList = new ArrayList<>();
        initializeMenu();
    }

    public List<Menu> getMenuList() {
        return Collections.unmodifiableList(menuList);
    }

    private void initializeMenu() {
        addAppetizer();
        addDessert();
        addBeverage();
        addMainCourse();
    }

    private void addAppetizer() {
        Collections.addAll(menuList, Appetizer.values());
    }

    private void addMainCourse() {
        Collections.addAll(menuList, MainCourse.values());
    }

    private void addDessert() {
        Collections.addAll(menuList, Dessert.values());
    }

    private void addBeverage() {
        Collections.addAll(menuList, Beverage.values());
    }
}
