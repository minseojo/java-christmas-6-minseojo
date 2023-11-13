package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.event.discount.*;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.EventfulOrder;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private final MenuDiscountEventManager menuDiscountEventManager;
    private final GlobalEventManager globalEventManager;
    private final BadgeManager badgeManager;
    private final EventBenefitCalculator eventBenefitCalculator;

    private final Order order;
    private final Map<Event, Price> eventBenefits;
    private final Map<Menu, Integer> eventGifts;
    private Badge badge;

    public EventManager(Order order) {
        this.order = order;
        this.badge = Badge.NONE;
        this.eventBenefits = new LinkedHashMap<>();
        this.eventGifts = new LinkedHashMap<>();

        this.menuDiscountEventManager = new MenuDiscountEventManager();
        this.globalEventManager = new GlobalEventManager(order);
        this.badgeManager = new BadgeManager();
        this.eventBenefitCalculator = new EventBenefitCalculator();
        addEvents();
    }

    private void addEvents() {
        // 출력 순서를 유지하기 위해, LinkedHashMap 에 먼저 넣어 놓는다.
        eventBenefits.put(ChristmasDiscount.INSTANCE, Price.zero());
        eventBenefits.put(WeekdayDiscount.INSTANCE, Price.zero());
        eventBenefits.put(WeekendDiscount.INSTANCE, Price.zero());
        eventBenefits.put(SpecialDiscount.INSTANCE, Price.zero());
        eventBenefits.put(ChampagneGift.INSTANCE, Price.zero());
    }

    public void applyEvents() {
        if (order.getOrderPrice() < 10000) {
            return;
        }
        menuDiscountEventManager.applyMenuDiscountEvents(order, eventBenefits);
        globalEventManager.applyGlobalEvents(eventBenefits, eventGifts);
        this.badge = badgeManager.applyEventBadge(order, getDiscountPrice(), getGiftPrice());
    }

    private double getDiscountPrice() {
        double discountPrice = 0.0;

        for (Map.Entry<Event, Price> entry : eventBenefits.entrySet()) {
            Event event = entry.getKey();
            Price eventPrice = entry.getValue();

            if (event instanceof DiscountEvent) {
                discountPrice += eventPrice.price();
            }
        }

        return discountPrice;
    }

    private double getGiftPrice() {
        double giftPrice = 0.0;

        for (Map.Entry<Event, Price> entry : eventBenefits.entrySet()) {
            Event event = entry.getKey();
            Price eventPrice = entry.getValue();

            if (event instanceof GiftEvent) {
                giftPrice += eventPrice.price();
            }
        }

        return giftPrice;
    }

    public Price getEventBenefitPrice() {
        return eventBenefitCalculator.getTotalEvnetBenefitPrice(getDiscountPrice(), getGiftPrice());
    }

    public Price getExceptedDiscountPrice() {
        return eventBenefitCalculator.getExceptedDiscountPrice(Price.of(order.getOrderPrice() - getDiscountPrice()).price());
    }

    public EventfulOrder createEventfulOrder() {
        return new EventfulOrder(order.getOrder(),
                Price.of(order.getOrderPrice()),
                eventGifts,
                eventBenefits,
                getEventBenefitPrice(),
                getExceptedDiscountPrice(),
                badge);
    }
}