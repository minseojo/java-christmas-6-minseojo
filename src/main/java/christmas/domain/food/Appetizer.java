package christmas.domain.food;

public enum Appetizer implements Menu {
    MUSHROOM_SOUP("양송이수프", 6000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    TAPAS("타파스", 5500.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    CAESAR_SALAD("시저샐러드", 8000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    };

    private final String name;
    private final double price;

    Appetizer(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static String description() {
        return "<애피타이저>";
    }
}
