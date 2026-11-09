package dno;

import java.time.LocalDateTime;

public class OptimizationService {

    public OptimizationRun loadRun(String id) {
        OptimizationRun run = new OptimizationRun(id, 24, 9820, 7430);
        run.addRecommendation(new Recommendation("ORD-2026-0128", "Linz Hub", 100, 170, "RECOMMENDED"));
        run.addRecommendation(new Recommendation("ORD-2026-0127", "Graz South", 100, 120, "RECOMMENDED"));
        run.addRecommendation(new Recommendation("ORD-2026-0126", "Vienna Central", 100, 145, "RECOMMENDED"));
        return run;
    }

    public void acceptRun(OptimizationRun run) {
        if (!run.isPending()) {
            throw new IllegalStateException("Run is not pending");
        }
        if (!run.isValidated()) {
            throw new IllegalStateException("Run is not validated");
        }
        run.setDecision("Accepted");
        run.setDecidedAt(LocalDateTime.now().toString());
    }

    public void rejectRun(OptimizationRun run) {
        if (!run.isPending()) {
            throw new IllegalStateException("Run is not pending");
        }
        run.setDecision("Rejected");
        run.setDecidedAt(LocalDateTime.now().toString());
    }
}
