package christmas.promotion.domain.event.manager;

import christmas.promotion.vo.Price;

public enum EventBenefitCalculator {
    INSTANCE;

    public Price getTotalEvnetBenefitPrice(double discountPrice, double giftPrice) {
        return Price.of(-1 * (discountPrice + giftPrice));
    }

    public Price getExceptedDiscountPrice(double discountPrice) {
        return Price.of(discountPrice);
    }
}

