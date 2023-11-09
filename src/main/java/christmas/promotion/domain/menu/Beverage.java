package christmas.promotion.domain.menu;

public enum Beverage implements Menu {
    ZERO_COLA("제로콜라", 3000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    RED_WINE("레드와인", 60000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    CHAMPAGNE("샴페인", 25000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    };

    private final String name;
    private final double price;

    Beverage(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static String description() {
        return "<음료>";
    }
}