package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.menu.Menu;

public interface GiftEvent extends Event, GlobalEvent {
    Menu getGiftMenu();

    Integer getGiftQuantity();

    default String getEventName() {
        return "증정 이벤트";
    }
}
