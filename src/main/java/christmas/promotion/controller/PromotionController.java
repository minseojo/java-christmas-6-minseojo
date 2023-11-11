package christmas.promotion.controller;

import christmas.promotion.domain.event.EventManager;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderParser;
import christmas.promotion.dto.BenefitDetailsDto;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;

import java.time.LocalDate;
import java.util.Map;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        displayDecemberWelcomeMessage();

        int visitDay = getVisitDay();

        String orderDetails = getOrderDetails();

        Map<String, Integer> orderMenus = paseOrderDetailsToMenus(orderDetails);

        displayEventBenefitsOnDecember(visitDay);
        outputView.printOrderMenus(orderMenus);

        Order order = new Order(orderMenus, LocalDate.of(2023, 12, visitDay), new MenuBoard());

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

    private void displayGiftMenus(Map<Menu, Integer> map) {
        outputView.printGiftMenus(map);
    }

    private void displayTotalOrderPriceBeforeDiscount(Order order) {
        outputView.printTotalOrderPriceBeforeDiscount(order.getOriginalPrice());
    }

    private void displayEventBenefitsOnDecember(int visitDay) {
        outputView.printEventBenefitsOnDecemberMessage(visitDay);
    }

    private void displayOrderMenus(Map<String, Integer> orderMenus) {
        outputView.printOrderMenus(orderMenus);
    }

    private static Map<String, Integer> paseOrderDetailsToMenus(String orderDetails) {
        Map<String, Integer> orderMenus = OrderParser.parseOrderDetails(orderDetails);
        return orderMenus;
    }

    private String getOrderDetails() {
        inputView.requestOrderDetails();
        String orderDetails = inputView.readOrderDetails();
        return orderDetails;
    }

    private int getVisitDay() {
        inputView.requestDecemberVisitDate();
        int visitDay = Integer.parseInt(inputView.readDecemberVisitDate());
        return visitDay;
    }

    private void displayDecemberWelcomeMessage() {
        outputView.printDecemberWelcomeMessage();
    }
}
