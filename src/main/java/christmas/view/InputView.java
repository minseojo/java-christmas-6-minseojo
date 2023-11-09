package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private enum Message {
        VISIT_DATE_IN_DECEMBER("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public void requestDecemberVisitDate() {
        System.out.print(Message.VISIT_DATE_IN_DECEMBER.message);
    }

    public String readDecemberVisitDate() {
        String decemberVisitData = Console.readLine();
        return decemberVisitData;
    }
}