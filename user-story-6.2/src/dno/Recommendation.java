package dno;

public class Recommendation {

    private String orderId;
    private String warehouse;
    private int coverage;
    private int cost;
    private String result;

    public Recommendation(String orderId, String warehouse, int coverage, int cost, String result) {
        this.orderId = orderId;
        this.warehouse = warehouse;
        this.coverage = coverage;
        this.cost = cost;
        this.result = result;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public int getCoverage() {
        return coverage;
    }

    public int getCost() {
        return cost;
    }

    public String getResult() {
        return result;
    }
}
