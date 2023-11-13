package christmas.promotion.domain.event.database.december2023;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.AtomicInteger;

public enum EventApplicationDatabase {
    INSTANCE;

    private final DoubleAdder salePrice = new DoubleAdder();
    private final AtomicInteger eventParticipationCount = new AtomicInteger(0);

    public void updateSalePrice(double salePrice) {
        this.salePrice.add(salePrice);
    }

    public void updateEventParticipationCount() {
        eventParticipationCount.incrementAndGet();
    }

    public double getSalePrice() {
        return salePrice.sum();
    }

    public int getEventParticipationCount() {
        return eventParticipationCount.get();
    }
}