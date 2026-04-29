package unlp.info.bd2.dto;

public class RouteDTO {
    private String routeName;
    private Long amountPurchases;
    private float averagePrice;

    public RouteDTO(String routeName, Long amountPurchases, float averagePrice) {
        this.routeName = routeName;
        this.amountPurchases = amountPurchases;
        this.averagePrice = averagePrice;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public long getAmountPurchases() {
        return this.amountPurchases;
    }

    public float getAveragePrice() {
        return this.averagePrice;
    }
}
