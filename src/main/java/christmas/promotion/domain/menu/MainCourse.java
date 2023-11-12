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

    private final String name;
    private final double price;
    private final List<DiscountEvent> discountEvents;

    MainCourse(String name, double price) {
        this.name = name;
        this.price = price;
        this.discountEvents = Collections.singletonList(WeekendDiscount.INSTANCE);
    }

    public String description() {
        return "<메인>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<DiscountEvent> getDiscountEvents() {
        return discountEvents;
    }
}
