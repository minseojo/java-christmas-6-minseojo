package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;

public interface DiscountEvent<T> extends Event<T> {
    @Override
    default String getEventName() {
        return "할인 이벤트";
    }
}
