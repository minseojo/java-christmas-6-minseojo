package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.menu.Menu;

public interface GiftEvent<T> extends GlobalEvent<T> {
    Menu getGiftMenu();

    Integer getGiftQuantity();

    @Override
    default String getEventName() {
        return "증정 이벤트";
    }
}

