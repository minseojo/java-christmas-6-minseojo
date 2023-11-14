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
        System.out.print(Message.DECEMBER_WELCOME.getMessage());
        printEmptyLine();
    }

    public void printEventBenefitsOnDecemberMessage(int visitDay) {
        System.out.printf(Message.DECEMBER_EVENT_BENEFITS.getMessage(), visitDay);
        printEmptyLines(2);
    }

    public void printOrderMenus(OrderMenusDto orderMenus) {
        System.out.print(Message.ORDER_MENUS.getMessage());
        printEmptyLine();

        for (OrderMenu orderMenu : orderMenus.orderMenus()) {
            String menuName = orderMenu.getMenu().getName();
            int menuQuantity = orderMenu.getQuantity().quantity();

            System.out.print(menuName + " " + menuQuantity + "개");
            printEmptyLine();
        }
        printEmptyLine();
    }

    public void printTotalOrderPriceBeforeDiscount(Price price) {
        System.out.print(Message.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        printEmptyLine();
        printPrice(price);
        printEmptyLine();
    }

    public void printGiftMenus(GiftMenusDto gifts) {
        System.out.print(Message.GIFT_MENUS.message);
        printEmptyLine();

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
        System.out.print(Message.BENEFIT_DETAILS.getMessage());
        printEmptyLine();

        eventBenefitsDto.eventBenefits().entrySet().stream()
                .filter(entry -> entry.getValue().price() > 0.0)
                .forEach(entry -> System.out.printf("%s: -%,.0f원\n",
                        entry.getKey().getEventName(), entry.getValue().price()));

        if (!hasBenefit(eventBenefitsDto)) {
            System.out.print("없음");
            printEmptyLine();
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
        System.out.print(Message.TOTAL_BENEFIT_PRICE.getMessage());
        printEmptyLine();
        printPrice(price);
        printEmptyLine();
    }

    public void printDiscountedFinalPrice(Price price) {
        System.out.print(Message.EXCEPTED_PAYMENT.getMessage());
        printEmptyLine();
        printPrice(price);
        printEmptyLine();
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.print(Message.DECEMBER_EVNET_BADGE.getMessage());
        printEmptyLine();
        System.out.print(badgeName);
        printEmptyLine();
    }

    public void printErrorMessage(String message) {
        System.out.print(message);
        printEmptyLine();
    }

    private void printPrice(Price price) {
        if (price.price() == 0.0) {
            System.out.printf("%,.0f원", Math.abs(price.price()));
            printEmptyLine();
            return;
        }

        System.out.printf("%,.0f원", price.price());
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private void printEmptyLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            printEmptyLine();
        }
    }
}
