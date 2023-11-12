package christmas.promotion.controller;

import christmas.promotion.domain.event.EventManager;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenusParser;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.dto.OrderMenusDto;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.VisitDay;

import java.time.LocalDate;
import java.util.Map;

import static christmas.promotion.constant.Constants.EVENT_MONTH;
import static christmas.promotion.constant.Constants.EVENT_YEAR;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        displayDecemberWelcomeMessage();

        VisitDay visitDay = processUserVisitDay();

        Order order = processOrder(visitDay);
        displayEventBenefitsOnDecember(visitDay);
        displayOrderMenus(order.toOrderMenusDto());


        displayTotalOrderPriceBeforeDiscount(order);
        EventManager eventManager = new EventManager(order);

        GiftMenusDto giftMenusDto = eventManager.getGiftMenusDto();
        displayGiftMenus(giftMenusDto);

        EventBenefitsDto eventBenefitsDto = eventManager.getEventBenefitsDto();
        outputView.printBenefitDetails(eventBenefitsDto);

        Price totalEvnetBenefitPrice = eventManager.getTotalEvnetBenefitPrice();
        outputView.printTotalBenefitPrice(totalEvnetBenefitPrice);

        Price exceptedPayMent = eventManager.getExceptedPayMent();
        outputView.printExceptedPayment(exceptedPayMent);

        eventManager.applyEventBadge();
        outputView.printDecemberEventBadge(order.getBadgeName());
    }

    private VisitDay processUserVisitDay() {
        while (true) {
            try {
                inputView.requestDecemberVisitDate();
                return new VisitDay(inputView.readDecemberVisitDate());
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }

    private Order processOrder(VisitDay visitDay) {
        while (true) {
            try {
                Map<String, Integer> orderMenus = getOrderMenuDetails();
                return new Order(orderMenus, LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay.getVisitDay()), new MenuBoard());
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Map<String, Integer> getOrderMenuDetails() {
        while (true) {
            try {
                inputView.requestOrderDetails();
                String orderMenuDetails = inputView.readOrderDetails();
                return parseOrderMenuDetails(orderMenuDetails);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Map<String, Integer> parseOrderMenuDetails(String orderDetails) {
        return OrderMenusParser.parse(orderDetails);
    }

    private void displayGiftMenus(GiftMenusDto giftMenus) {
        outputView.printGiftMenus(giftMenus);
    }

    private void displayTotalOrderPriceBeforeDiscount(Order order) {
        outputView.printTotalOrderPriceBeforeDiscount(order.calculateTotal());
    }

    private void displayEventBenefitsOnDecember(VisitDay visitDay) {
        outputView.printEventBenefitsOnDecemberMessage(visitDay.getVisitDay());
    }

    private void displayOrderMenus(OrderMenusDto orderMenus) {
        outputView.printOrderMenus(orderMenus);
    }

    private void displayDecemberWelcomeMessage() {
        outputView.printDecemberWelcomeMessage();
    }
}
