package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.dto.OrderMenusDto;
import christmas.promotion.vo.Price;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventfulOrder {
    private final List<OrderMenu> orderMenus; // 주문 메뉴
    private final Price originalPrice; // 할인 전 총 주문 금액
    private final Map<Menu, Integer> giftMenus; // 증정 메뉴
    private final Map<Event, Price> eventBenefits; // 혜택 내역
    private final Price totalBenefitPrice; // 총 혜택 금액
    private final Price discountedPrice; // 할인 후 예상 결제 금액
    private final Badge badge; // 배지


    public EventfulOrder(List<OrderMenu> orderMenus,
                         Price originalPrice,
                         Map<Menu, Integer> giftMenus,
                         Map<Event, Price> eventBenefits,
                         Price totalBenefitPrice,
                         Price discountedPrice,
                         Badge badge) {

        this.orderMenus = List.copyOf(orderMenus);
        this.originalPrice = originalPrice;
        this.giftMenus = Collections.unmodifiableMap(giftMenus);
        this.eventBenefits = Collections.unmodifiableMap(eventBenefits);
        this.totalBenefitPrice = totalBenefitPrice;
        this.discountedPrice = discountedPrice;
        this.badge = badge;
    }

    public OrderMenusDto getOrderMenusDto() {
        return new OrderMenusDto(orderMenus);
    }

    public Price getOriginalPrice() {
        return originalPrice;
    }

    public GiftMenusDto getGiftMenusDto() {
        return new GiftMenusDto(giftMenus);
    }

    public EventBenefitsDto getEventBenefitsDto() {
        return new EventBenefitsDto(eventBenefits);
    }

    public Price getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public Price getDiscountedPrice() {
        return discountedPrice;
    }

    public Badge getBadge() {
        return badge;
    }
}
