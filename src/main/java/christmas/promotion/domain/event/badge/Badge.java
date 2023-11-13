package christmas.promotion.domain.event.badge;

import christmas.promotion.domain.visitdate.VisitDate;

import java.time.LocalDate;

public enum Badge {
    NONE("없음", 0.0),
    STAR("별", 5000.0),
    TREE("트리", 10000.0),
    SANTA("산타", 20000.0);

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final double BADGE_MINIMUM_QUALIFY = STAR.threshold;

    private final String name;
    private final double threshold;

    Badge(String name, double threshold) {
        this.name = name;
        this.threshold = threshold;  // 수정: Badge.None 대신에 threshold 값을 할당
    }

    public static boolean isPossibleEvent(VisitDate date, double discountPrice) {
        return isBetweenDates(date) && discountPrice >= BADGE_MINIMUM_QUALIFY;
    }

    private static boolean isBetweenDates(VisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }

    public static Badge applyEvent(VisitDate date, double discountPrice) {
        if (!isPossibleEvent(date, discountPrice)) {
            return NONE;
        }

        Badge newBadge = NONE;
        for (Badge badge : Badge.values()) {
            if (badge.isPriceExceedingBadgeThreshold(discountPrice)) {
                newBadge = badge;
            }
        }
        return newBadge;
    }

    private boolean isPriceExceedingBadgeThreshold(double price) {
        return price >= threshold;
    }

    public String getName() {
        return name;
    }
}
