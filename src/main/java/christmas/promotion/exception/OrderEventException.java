package christmas.promotion.exception;

public class OrderEventException extends IllegalArgumentException {
    public enum ErrorMessage {
        ORDER_EVENT_ERROR("[ERROR] 할인을 적용한 총 가격이 0원 보다 커야합니다. 다시 입력해 주세요.");

        ErrorMessage(String message) {
            this.message = message;
        }

        private final String message;

        public String getMessage() {
            return message;
        }
    }

    public OrderEventException() {
        super(ErrorMessage.ORDER_EVENT_ERROR.getMessage());
    }
}