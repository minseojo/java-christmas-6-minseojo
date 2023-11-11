package christmas.promotion.view;

import christmas.promotion.domain.event.Event;

import java.util.Map;

public class OutputView {
    private enum Message {
        DECEMBER_WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        DECEMBER_EVENT_BENEFITS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
        ORDER_MENUS("<주문 메뉴>"),
        TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
        GIFT_MENUS("<증정 메뉴>"),
        BENEFIT_DETAILS("<혜택 내역>"),
        TOTAL_BENEFIT_PRICE("<총혜택 금액>"),
        EXCEPTED_PAYMENT("<할인 후 예상 결제 금액>"),
        DECEMBER_EVNET_BADGE("<12월 이벤트 배지>");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public void printDecemberWelcomeMessage() {
        System.out.print(Message.DECEMBER_WELCOME.getMessage() + "\n");
    }

    public void printEventBenefitsOnDecemberMessage(int day) {
        System.out.print(String.format(Message.DECEMBER_EVENT_BENEFITS.getMessage(), day) + "\n\n");
    }

    public void printOrderMenus(Map<String, Integer> orderMenus) {
        System.out.println(Message.ORDER_MENUS.getMessage());
        for (Map.Entry<String, Integer> orderMenu : orderMenus.entrySet()) {
            System.out.println(orderMenu.getKey() + " " + orderMenu.getValue() + "개");
        }
        printEmptyLine();
    }

    public void printTotalOrderPriceBeforeDiscount(double price) {
        System.out.println(Message.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printGiftMenus(Map<String, Integer> gifts) {
        System.out.println(Message.GIFT_MENUS.message);
        for (String gift : gifts.keySet()) {
            System.out.println(gift + " " + gifts.get(gift) + "개");
        }
        printEmptyLine();
    }

    public void printBenefitDetails(Map<Event, Double> benefitDetails) {
        System.out.println(Message.BENEFIT_DETAILS.getMessage());

        benefitDetails.entrySet().stream()
                .filter(entry -> entry.getValue() > 0.0)
                .forEach(entry -> System.out.printf("%s: -%,.0f%n",
                        entry.getKey().getEventName(), entry.getValue()));

        if (!hasBenefit(benefitDetails)) {
            System.out.println("없음");
        }

        printEmptyLine();
    }

    private boolean hasBenefit(Map<Event, Double> benefitDetails) {
        for (Double value : benefitDetails.values()) {
            if (value > 0.0) {
                return true;
            }
        }
        return false;
    }

    public void printTotalBenefitPrice(double price) {
        System.out.println(Message.TOTAL_BENEFIT_PRICE.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printExpectedPayment(double price) {
        System.out.println(Message.EXCEPTED_PAYMENT.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.println(Message.DECEMBER_EVNET_BADGE.getMessage());
        System.out.println(badgeName);
    }

    private static void printPrice(double price) {
        System.out.printf("%,.0f원%n", price);
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
