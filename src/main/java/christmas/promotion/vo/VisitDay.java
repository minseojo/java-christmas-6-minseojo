package christmas.promotion.vo;

import christmas.promotion.exception.VisitDayException;

import java.util.Objects;

public class VisitDay {
    private static final int DECEMBER_START_DAY = 1;
    private static final int DECEMBER_END_DAY = 31;

    //Integer 는 -128 ~ 127까지 캐싱 되므로, 1 ~ 31일은 캐싱 데이터를 안 만들었다.
    private final Integer visitDay;

    public VisitDay(String visitDay) {
        validate(visitDay);
        this.visitDay = Integer.parseInt(visitDay);
    }

    private void validate(String visitDay) {
        validateInteger(visitDay);
        validateRangeVisitDay(visitDay);
    }

    private static void validateInteger(String visitDay) {
        try {
            Integer.parseInt(visitDay);
        } catch (IllegalArgumentException exception) {
            throw new VisitDayException();
        }
    }

    private static void validateRangeVisitDay(String visitDay) {
        int value = Integer.parseInt(visitDay);
        if (value < DECEMBER_START_DAY || value > DECEMBER_END_DAY) {
            throw new VisitDayException();
        }
    }

    public Integer getVisitDay() {
        return visitDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VisitDay other = (VisitDay) o;

        return Objects.equals(visitDay, other.visitDay);
    }

    @Override
    public int hashCode() {
//        validate 를 통해, null이 될 수 없음.
//        if (visitDay == null) {
//            return 0;
//        }

        return visitDay.hashCode();
    }
}
