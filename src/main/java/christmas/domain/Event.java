package christmas.domain;

import java.time.LocalDate;

interface Event {
    int applyDiscount(int originalPrice, LocalDate currentDate);
}
