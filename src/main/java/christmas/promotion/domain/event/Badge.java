package christmas.promotion.domain.event;

public enum Badge {
    STAR(5000.0),
    TREE(10000.0),
    SANTA(20000.0);

    private final double minimumTotalBenefit;

    Badge(double minimumTotalBenefit) {
        this.minimumTotalBenefit = minimumTotalBenefit;
    }

    public double getMinimumTotalBenefit() {
        return minimumTotalBenefit;
    }
}
