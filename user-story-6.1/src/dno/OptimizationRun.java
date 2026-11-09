package dno;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OptimizationRun {

    private String id;
    private int ordersOptimized;
    private int baselineCost;
    private int optimizedCost;
    private String decision;
    private List<Recommendation> recommendations = new ArrayList<>();

    public OptimizationRun(String id, int ordersOptimized, int baselineCost, int optimizedCost) {
        this.id = id;
        this.ordersOptimized = ordersOptimized;
        this.baselineCost = baselineCost;
        this.optimizedCost = optimizedCost;
        this.decision = "Pending";
    }

    public void addRecommendation(Recommendation r) {
        recommendations.add(r);
    }

    public String getId() {
        return id;
    }

    public int getOrdersOptimized() {
        return ordersOptimized;
    }

    public int getBaselineCost() {
        return baselineCost;
    }

    public int getOptimizedCost() {
        return optimizedCost;
    }

    public String getDecision() {
        return decision;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public int getWarehouseCount() {
        Set<String> names = new HashSet<>();
        for (Recommendation r : recommendations) {
            names.add(r.getWarehouse());
        }
        return names.size();
    }

    public int getRecommendedPlans() {
        for (Recommendation r : recommendations) {
            if (r.getResult().equals("RECOMMENDED")) {
                return 1;
            }
        }
        return 0;
    }

    public boolean isValidated() {
        if (recommendations.isEmpty()) {
            return false;
        }
        for (Recommendation r : recommendations) {
            if (r.getCoverage() < 100 || r.getResult().equals("INSUFFICIENT")) {
                return false;
            }
        }
        return true;
    }
}
