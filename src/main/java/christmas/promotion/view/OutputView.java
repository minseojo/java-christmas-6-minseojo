package christmas.promotion.view;

import christmas.promotion.domain.order.OrderMenu;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.dto.OrderMenusDto;
import christmas.promotion.vo.Price;

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

    public void printOrderMenus(OrderMenusDto orderMenus) {
        System.out.println(Message.ORDER_MENUS.getMessage());
        for (OrderMenu orderMenu : orderMenus.orderMenus()) {
            String menuName = orderMenu.getMenu().getName();
            int menuQuantity = orderMenu.getQuantity().quantity();
            System.out.println(menuName + " " + menuQuantity + "개");
        }
        printEmptyLine();
    }

    public void printTotalOrderPriceBeforeDiscount(Price price) {
        System.out.println(Message.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printGiftMenus(GiftMenusDto gifts) {
        System.out.println(Message.GIFT_MENUS.message);

        if (gifts.getGiftMenusSize() == 0) {
            System.out.println("없음");
            printEmptyLine();
            return;
        }

        gifts.giftMenus().forEach(
                (menu, quantity) ->
                        System.out.println(menu.getName() + " " + quantity + "개"));

        printEmptyLine();
    }

    public void printBenefitDetails(EventBenefitsDto eventBenefitsDto) {
        System.out.println(Message.BENEFIT_DETAILS.getMessage());

        eventBenefitsDto.eventBenefits().entrySet().stream()
                .filter(entry -> entry.getValue().price() > 0.0)
                .forEach(entry -> System.out.printf("%s: -%,.0f원\n",
                        entry.getKey().getEventName(), entry.getValue().price()));

        if (!hasBenefit(eventBenefitsDto)) {
            System.out.println("없음");
        }

        printEmptyLine();
    }

    private boolean hasBenefit(EventBenefitsDto eventBenefitsDto) {
        for (Price value : eventBenefitsDto.eventBenefits().values()) {
            if (value.price() > 0.0) {
                return true;
            }
        }
        return false;
    }

    public void printTotalBenefitPrice(Price price) {
        System.out.println(Message.TOTAL_BENEFIT_PRICE.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printExceptedPayment(Price price) {
        System.out.println(Message.EXCEPTED_PAYMENT.getMessage());
        printPrice(price);
        printEmptyLine();
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.println(Message.DECEMBER_EVNET_BADGE.getMessage());
        System.out.println(badgeName);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printPrice(Price price) {
        if (price.price() == 0.0) {
            System.out.printf("%,.0f원%n", Math.abs(price.price()));
            return;
        }

        System.out.printf("%,.0f원%n", price.price());
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
