package christmas.promotion.controller;

import christmas.promotion.domain.event.manager.EventManager;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.EventfulOrder;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenusParser;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.EventfulOrderDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.exception.OrderMenuException;
import christmas.promotion.exception.VisitDayException;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.VisitDate;

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

        VisitDate visitDate = processUserVisitDateTransaction();

        Order order = processOrderTransaction(visitDate);
        displayEventBenefitsOnDecember(visitDate);

        EventfulOrder eventfulOrder = processOrderEventTransaction(order);
        displayEventfulOrder(eventfulOrder);
    }

    private void displayDecemberWelcomeMessage() {
        outputView.printDecemberWelcomeMessage();
    }

    private VisitDate processUserVisitDateTransaction() {
        while (true) {
            try {
                inputView.requestDecemberVisitDate();
                String userVisitDay = inputView.readDecemberVisitDay();
                return new VisitDate(userVisitDay);
            } catch (VisitDayException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Order processOrderTransaction(VisitDate visitDate) {
        MenuBoard menuBoard = new MenuBoard();

        while (true) {
            try {
                inputView.requestOrderDetails();
                String orderMenuDetails = inputView.readOrderDetails();
                Map<String, Integer> orderMenus = parseOrderMenuDetails(orderMenuDetails);
                return new Order(orderMenus, visitDate ,menuBoard);
            } catch (OrderMenuException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Map<String, Integer> parseOrderMenuDetails(String orderDetails) {
        return OrderMenusParser.parse(orderDetails);
    }

    private void displayEventBenefitsOnDecember(VisitDate visitDate) {
        outputView.printEventBenefitsOnDecemberMessage(visitDate.getVisitDay());
    }

    private EventfulOrder processOrderEventTransaction(Order order) {
        EventManager eventManager = new EventManager(order);
        eventManager.applyEvents();
        return  eventManager.createEventfulOrder();
    }

    private void displayEventfulOrder(EventfulOrder eventfulOrder) {
        EventfulOrderDto eventfulOrderDto = eventfulOrder.toEventfulOrderDto();

        displayOrderMenus(eventfulOrderDto);                       // 주문 메뉴
        displayTotalOrderPriceBeforeDiscount(eventfulOrderDto);    // 할인 전 총 주문 금액
        displayGiftMenus(eventfulOrderDto);                        // 증정 메뉴
        displayEvnetBenefits(eventfulOrderDto);                    // 혜택 내역
        displayTotalEventBenefitsPrice(eventfulOrderDto);          // 총 혜택 금액
        displayExceptedPayMent(eventfulOrderDto);                  // 할인 후 예상 결제 금액
        displayEventBadge(eventfulOrderDto);                       // 이벤트 배지
    }

    private void displayOrderMenus(EventfulOrderDto eventfulOrder) {
        outputView.printOrderMenus(eventfulOrder.getOrderMenus());
    }

    private void displayTotalOrderPriceBeforeDiscount(EventfulOrderDto eventfulOrder) {
        outputView.printTotalOrderPriceBeforeDiscount(eventfulOrder.getOriginalPrice());
    }

    private void displayGiftMenus(EventfulOrderDto eventfulOrder) {
        GiftMenusDto giftMenusDto = eventfulOrder.getGiftMenus();
        outputView.printGiftMenus(giftMenusDto);
    }

    private void displayEvnetBenefits(EventfulOrderDto eventfulOrder) {
        EventBenefitsDto eventBenefitsDto = eventfulOrder.getEventBenefits();
        outputView.printBenefitDetails(eventBenefitsDto);
    }

    private void displayTotalEventBenefitsPrice(EventfulOrderDto eventfulOrder) {
        Price totalEvnetBenefitPrice = eventfulOrder.getTotalBenefitPrice();
        outputView.printTotalBenefitPrice(totalEvnetBenefitPrice);
    }

    private void displayExceptedPayMent(EventfulOrderDto eventfulOrder) {
        Price exceptedPayMent = eventfulOrder.getDiscountedPrice();
        outputView.printExceptedPayment(exceptedPayMent);
    }

    private void displayEventBadge(EventfulOrderDto eventfulOrder) {
        outputView.printDecemberEventBadge(eventfulOrder.getBadge().getName());
    }
}
