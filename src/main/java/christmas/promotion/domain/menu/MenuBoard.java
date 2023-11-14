package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.WeekdayDiscount;
import christmas.promotion.domain.event.discount.WeekendDiscount;
import christmas.promotion.exception.OrderMenuException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MenuBoard {

    private final List<EventfulMenu> menuBoard;

    public MenuBoard() {
        this.menuBoard = new ArrayList<>();
        initializeMenu();
    }

    public EventfulMenu findMenu(String name) {
        for (EventfulMenu menu : menuBoard) {
            if (name.equals(menu.getName())) {
                return menu;
            }
        }

        throw new OrderMenuException();
    }

    private void initializeMenu() {
        addAppetizer();
        addMainCourse();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        for (Appetizer value : Appetizer.values()) {
            menuBoard.add(EventfulMenu.createMenuItem(value));
        }
    }

    private void addMainCourse() {
        for (MainCourse value : MainCourse.values()) {
            menuBoard.add(EventfulMenu.createMenuItemWithDiscount(value, Collections.singletonList(WeekendDiscount.INSTANCE)));
        }
    }

    private void addDessert() {
        for (Dessert value : Dessert.values()) {
            menuBoard.add(EventfulMenu.createMenuItemWithDiscount(value, Collections.singletonList(WeekdayDiscount.INSTANCE)));
        }
    }

    private void addBeverage() {
        for (Beverage value : Beverage.values()) {
            menuBoard.add(EventfulMenu.createMenuItem(value));
        }
    }

    public List<EventfulMenu> getmenuBoard() {
        return menuBoard;
    }
}
