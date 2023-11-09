package christmas.promotion.domain.food;

public enum MainCourse implements Menu {
    T_BONE_STEAK("티본스테이크", 55000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    BARBECUE_RIBS("바비큐립", 54000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    SEAFOOD_PASTA("해산물파스타", 35000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    },
    CHRISTMAS_PASTA("크리스마스파스타", 25000.0) {
        @Override
        public void applyDiscount() {
            // 할인 로직 구현
        }
    };

    private final String name;
    private final double price;

    MainCourse(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static String description() {
        return "<메인>";
    }
}