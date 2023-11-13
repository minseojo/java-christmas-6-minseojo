package christmas.promotion.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private enum Message {
        VISIT_DAY_IN_DECEMBER("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        ORDER_DETAILS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public void requestDecemberVisitDate() {
        System.out.println(Message.VISIT_DAY_IN_DECEMBER.message);
    }

    public void requestOrderDetails() {
        System.out.println(Message.ORDER_DETAILS.message);
    }

    public String readDecemberVisitDay() {
        String decemberVisitData = Console.readLine();
        validateDecemberVisitDate(decemberVisitData);
        return decemberVisitData;
    }

    public String readOrderDetails() {
        String orderDetails = Console.readLine();
        validateOrderDetails(orderDetails);
        return orderDetails;
    }

    private void validateDecemberVisitDate(String decemberVisitData) {
        int dateMaxLength = 2;
        if (decemberVisitData.isBlank() || decemberVisitData.length() > dateMaxLength) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOrderDetails(String orderDetails) {
        int orderDetailsMaxLength = 1000;
        if(orderDetails.isBlank() || orderDetails.length() > orderDetailsMaxLength) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}