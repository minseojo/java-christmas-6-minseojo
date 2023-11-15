package christmas.promotion.controller;

import christmas.promotion.domain.event.manager.EventManager;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.EventfulOrder;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenusParser;
import christmas.promotion.dto.EventBenefitsDto;
import christmas.promotion.dto.EventfulOrderDto;
import christmas.promotion.dto.GiftMenusDto;
import christmas.promotion.exception.OrderEventException;
import christmas.promotion.exception.OrderMenuException;
import christmas.promotion.exception.VisitDayException;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

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
        DecemberVisitDate decemberVisitDate = processUserVisitDateTransaction();
        EventfulOrder eventfulOrder = processOrderTransaction(decemberVisitDate);
        displayEventfulOrder(eventfulOrder);
    }

    private void displayDecemberWelcomeMessage() {
        outputView.printDecemberWelcomeMessage();
    }

    private DecemberVisitDate processUserVisitDateTransaction() {
        while (true) {
            try {
                inputView.requestDecemberVisitDate();
                String userVisitDay = inputView.readDecemberVisitDay();
                return new DecemberVisitDate(userVisitDay);
            } catch (VisitDayException exception) {
                exception.printStackTrace();
                outputView.printErrorMessage(exception.getMessage());
                /**
                 * VisitDayException
                 * - 숫자가 아닌 경우
                 * - 숫자 범위가 [1, 31] 이 아닌 경우
                 */
            }
        }
    }

    private EventfulOrder processOrderTransaction(DecemberVisitDate decemberVisitDate) {
        while (true) {
            try {
                Order order = createOrder(decemberVisitDate);
                return processOrderEventTransaction(order);
            } catch (OrderMenuException | OrderEventException exception) {
                exception.printStackTrace();
                outputView.printErrorMessage(exception.getMessage());
                /**
                 * OrderMenuException
                 * - 주문 형식이 맞지 않는 경우
                 * - 메뉴판(MenuBoard)에 없는 메뉴를 고른 경우
                 * - 메뉴 개수가 1이상이 아닌 경우
                 * - 메뉴 개수를 총 20개를 넘긴 경우 (한번에 20개 까지만 가능)
                 * - 중복된 주문을 한 경우
                 *
                 * OrderEventException, 현재는 없는 상황
                 * - 할인 된 가격이 음수인 경우
                 */
            }
        }
    }

    private Order createOrder(DecemberVisitDate decemberVisitDate) {
        MenuBoard menuBoard = new MenuBoard();

        Map<String, Integer> orderMenus = getOrderMenus();
        Order order = new Order(orderMenus, decemberVisitDate, menuBoard);

        displayEventBenefitsOnDecember(decemberVisitDate);
        return order;
    }

    private Map<String, Integer> getOrderMenus() {
        while (true) {
            try {
                inputView.requestOrderDetails();
                String orderMenuDetails = inputView.readOrderDetails();
                return parseOrderMenuDetails(orderMenuDetails);
            } catch (OrderMenuException exception) {
                exception.printStackTrace();
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Map<String, Integer> parseOrderMenuDetails(String orderDetails) {
        return OrderMenusParser.parse(orderDetails);
    }

    private void displayEventBenefitsOnDecember(DecemberVisitDate decemberVisitDate) {
        outputView.printEventBenefitsOnDecemberMessage(decemberVisitDate.getVisitDay());
    }

    private EventfulOrder processOrderEventTransaction(Order order) {
        EventManager eventManager = new EventManager(order);
        eventManager.applyEvents();
        return eventManager.createEventfulOrder();
    }

    private void displayEventfulOrder(EventfulOrder eventfulOrder) {
        EventfulOrderDto eventfulOrderDto = eventfulOrder.toEventfulOrderDto();

        displayOrderMenus(eventfulOrderDto);                       // 주문 메뉴
        displayTotalOrderPriceBeforeDiscount(eventfulOrderDto);    // 할인 전 총 주문 금액
        displayGiftMenus(eventfulOrderDto);                        // 증정 메뉴
        displayEventBenefits(eventfulOrderDto);                    // 혜택 내역
        displayTotalEventBenefitsPrice(eventfulOrderDto);          // 총 혜택 금액
        displayDiscountedFinalPrice(eventfulOrderDto);                  // 할인 후 예상 결제 금액
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

    private void displayEventBenefits(EventfulOrderDto eventfulOrder) {
        EventBenefitsDto eventBenefitsDto = eventfulOrder.getEventBenefits();
        outputView.printBenefitDetails(eventBenefitsDto);
    }

    private void displayTotalEventBenefitsPrice(EventfulOrderDto eventfulOrder) {
        Price totalEventBenefitPrice = eventfulOrder.getTotalBenefitPrice();
        outputView.printTotalBenefitPrice(totalEventBenefitPrice);
    }

    private void displayDiscountedFinalPrice(EventfulOrderDto eventfulOrder) {
        Price exceptedPayment = eventfulOrder.getDiscountedFinalPrice();
        outputView.printDiscountedFinalPrice(exceptedPayment);
    }

    private void displayEventBadge(EventfulOrderDto eventfulOrder) {
        outputView.printDecemberEventBadge(eventfulOrder.getBadgeName());
    }
}
