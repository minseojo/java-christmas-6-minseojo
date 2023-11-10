package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.WeekendDiscount;

import java.util.Collections;
import java.util.List;

public enum MainCourse implements Menu {
    T_BONE_STEAK("티본스테이크", 55000.0),
    BARBECUE_RIBS("바비큐립", 54000.0),
    SEAFOOD_PASTA("해산물파스타", 35000.0),
    CHRISTMAS_PASTA("크리스마스파스타", 25000.0);

    private final MenuItem mainCourse;

    MainCourse(String name, double price) {
        this.mainCourse = new MenuItem(name, price, createDiscountEvents());
    }

    @Override
    public String description() {
        return "<메인>";
    }

    @Override
    public String getName() {
        return mainCourse.getName();
    }

    @Override
    public double getPrice() {
        return mainCourse.getPrice();
    }

    @Override
    public void applyDiscount() {
        mainCourse.applyDiscount();
    }

    @Override
    public List<DiscountEvent> getDiscountEvents() {
        return mainCourse.getDiscountEvents();
    }

    private List<DiscountEvent> createDiscountEvents() {
        return Collections.singletonList(new WeekendDiscount());
    }
}