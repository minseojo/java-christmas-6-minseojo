package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.Bill;

import java.time.LocalDate;

public interface GiftEvent {
    void applyGift(Bill bill, LocalDate currentDate);
}
