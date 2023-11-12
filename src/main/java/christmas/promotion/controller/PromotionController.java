package christmas.promotion.controller;

import christmas.promotion.domain.event.EventManager;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderParser;
import christmas.promotion.dto.BenefitDetailsDto;
import christmas.promotion.dto.OrderMenusDto;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;
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
        displayOrderMenus(order.toOrderMenusDto());

        displayEventBenefitsOnDecember(visitDay);

        displayTotalOrderPriceBeforeDiscount(order);
        EventManager eventManager = new EventManager(order);

        displayGiftMenus(eventManager.getGiftMenus());
        BenefitDetailsDto benefitDetailsDto = new BenefitDetailsDto(eventManager.getEvents());
        outputView.printBenefitDetails(benefitDetailsDto.getBenefitDetails());
        outputView.printTotalBenefitPrice(eventManager.getSalePrice() + eventManager.getGiftPrice());
        outputView.printExpectedPayment(order.getOriginalPrice() - eventManager.getSalePrice());

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
        return OrderParser.parse(orderDetails);
    }

    private void displayGiftMenus(Map<Menu, Integer> map) {
        outputView.printGiftMenus(map);
    }

    private void displayTotalOrderPriceBeforeDiscount(Order order) {
        outputView.printTotalOrderPriceBeforeDiscount(order.getOriginalPrice());
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
