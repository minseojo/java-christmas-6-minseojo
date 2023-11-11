package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.WeekdayDiscount;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public enum Dessert implements Menu, LocalEvent {
    CHOCOLATE_CAKE("초코케이크", 15000.0),
    ICE_CREAM("아이스크림", 5000.0);

    private final String name;
    private final double price;

    Dessert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String description() {
        return "<디저트>";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public List<DiscountEvent> getDiscountEvents() {
        return Collections.singletonList(WeekdayDiscount.INSTANCE);
    }

    @Override
    public double applyEvent(LocalDate date, double price) {
        return WeekdayDiscount.INSTANCE.applyEvent(date, price);
    }
}
