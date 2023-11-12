package christmas.promotion.domain.event.manager;

// ... (다른 import 생략)

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.vo.Price;

import java.util.Map;

public class EventBenefitCalculator {

    public Price getTotalEvnetBenefitPrice(double discountPrice, double giftPrice) {
        return Price.of(-1 * (discountPrice + giftPrice));
    }

    public Price getExceptedDiscountPrice(double discountPrice) {
        return Price.of(discountPrice);
    }

    public GiftMenusDto getGiftMenusDto(Map<Menu, Integer> eventGifts) {
        return new GiftMenusDto(eventGifts);
    }

    public EventBenefitsDto getEventBenefitsDto(Map<Event, Price> eventBenefits) {
        return new EventBenefitsDto(eventBenefits);
    }
}

