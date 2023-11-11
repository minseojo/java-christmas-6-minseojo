package christmas.promotion.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private enum Message {
        VISIT_DATE_IN_DECEMBER("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        ORDER_DETAILS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public void requestDecemberVisitDate() {
        System.out.println(Message.VISIT_DATE_IN_DECEMBER.message);
    }

    public String readDecemberVisitDate() {
        String decemberVisitData = Console.readLine();
        return decemberVisitData;
    }

    public void requestOrderDetails() {
        System.out.println(Message.ORDER_DETAILS.message);
    }

    public String readOrderDetails() {
        String decemberVisitData = Console.readLine();
        return decemberVisitData;
    }
}