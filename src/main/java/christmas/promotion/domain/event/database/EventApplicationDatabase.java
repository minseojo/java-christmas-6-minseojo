package christmas.promotion.domain.event.database;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.AtomicInteger;

public enum EventApplicationDatabase {
    INSTANCE;

    // 스레드 세이프하기 위해, DoubleAdder, AtomicInteger 이용
    private final DoubleAdder eventfulMenuSalePrice = new DoubleAdder();
    private final AtomicInteger eventParticipationCount = new AtomicInteger(0);

    public void updateSalePrice(double eventfulMenuSalePrice) {
        this.eventfulMenuSalePrice.add(eventfulMenuSalePrice);
    }

    public void updateEventParticipationCount() {
        eventParticipationCount.incrementAndGet();
    }

    public double getSalePrice() {
        return eventfulMenuSalePrice.sum();
    }

    public int getEventParticipationCount() {
        return eventParticipationCount.get();
    }
}