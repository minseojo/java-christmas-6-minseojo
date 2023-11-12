package christmas.promotion.domain.event.badge;

import java.time.LocalDate;

public enum Badge {
    NONE("없음", 0.0),
    STAR("별", 5000.0),
    TREE("트리", 10000.0),
    SANTA("산타", 20000.0);

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);

    private final String name;
    private final double threshold;

    Badge(String name, double threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static boolean isPossibleEvent(LocalDate date) {
        return !date.isBefore(EVENT_PERIOD_START) && !date.isAfter(EVENT_PERIOD_END);
    }

    public static Badge applyEvent(double price) {
        Badge badge = NONE;
        for (Badge value : Badge.values()) {
            if (isPriceExceedingBadgeThreshold(price, value)) {
                badge = value;
            }
        }
        return badge;
    }

    private static boolean isPriceExceedingBadgeThreshold(double price, Badge value) {
        return price >= value.threshold;
    }

    public String getName() {
        return name;
    }
}
