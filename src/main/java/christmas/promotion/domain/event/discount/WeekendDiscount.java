package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.Bill;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements DiscountEvent {
    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final int SUNDAY_VALUE = 0;
    private static final int THURSDAY_VALUE = 4;
    private static final int DAY_OF_WEEK_SIZE = 7;

    public double applyDiscount(Bill bill, LocalDate currentDate) {
//        if (isBetweenDates(currentDate)) {
//            int dayValue = calculateDayValue(currentDate);
//            if (SUNDAY_VALUE <= dayValue && dayValue <= THURSDAY_VALUE) {
//                for (Menu menu : bill.getMenus()) {
//                }
//            }
//            double discountAmount = calculateDayValue(currentDate);
//            return discountAmount;
//        }

        return 0;
    }

    private int calculateDayValue(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return (dayOfWeek.getValue() % DAY_OF_WEEK_SIZE) + 1;
    }

    @Override
    public double applyDiscount(double price) {
        return 0;
    }
}
