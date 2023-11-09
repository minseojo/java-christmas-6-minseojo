package christmas.promotion.domain.food;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000.0),
    ICE_CREAM("아이스크림", 5000.0);

    private final String name;
    private final double price;

    Dessert(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void applyDiscount() {
        // 할인 로직 구현
    }

    public static String description() {
        return "<디저트>";
    }
}