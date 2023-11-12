package christmas.promotion.vo;

public class VisitDay {
    //Integer 는 -128 ~ 127까지 캐싱 되므로, 1 ~ 31일은 캐싱 데이터를 안 만들었다.

    private final Integer visitDay;

    public VisitDay(String visitDay) {
        validate(visitDay);
        this.visitDay = Integer.parseInt(visitDay);
    }

    private void validate(String visitDay) {
        try {
            Integer.parseInt(visitDay);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        int value = Integer.parseInt(visitDay);
        if (value < 1 || value > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Integer getVisitDay() {
        return visitDay;
    }
}
