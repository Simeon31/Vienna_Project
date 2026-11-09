package dno;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PageBuilder {

    private static String[] icons = {"D", "A", "W", "S", "O", "O", "A", "H"};
    private static String[] names = {"Dashboard", "Accounts", "Warehouses", "Shipments", "Store Orders",
            "Optimization", "Assigned Work", "History"};

    private static String euro(int amount) {
        return "€" + NumberFormat.getInstance(Locale.US).format(amount);
    }

    public static String buildPage(OptimizationRun run) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n");
        html.append("<meta charset=\"UTF-8\">\n");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
        html.append("<title>DNO - Optimization &amp; Recommendations</title>\n");
        html.append("<link rel=\"stylesheet\" href=\"/style.css\">\n");
        html.append("</head>\n<body>\n<div class=\"app\">\n");
        html.append(buildSidebar());
        html.append("<main class=\"main\">\n");
        html.append("<div class=\"header\">\n<div>\n");
        html.append("<h1>Optimization &amp; Recommendations</h1>\n");
        html.append("<p id=\"subtitle\">Examine recommended fulfillment warehouses before taking a decision.</p>\n");
        html.append("</div>\n<span class=\"badge\">MANAGER</span>\n</div>\n");
        html.append("<div class=\"content\">\n");
        html.append(buildReviewCard(run));
        html.append("<div class=\"overlay\" id=\"overlay\">\n");
        html.append(buildAcceptDialog(run));
        html.append("</div>\n</div>\n");
        html.append("<div class=\"footer\" id=\"footer\">US 6.1 • SAMPLE DATA</div>\n");
        html.append("</main>\n</div>\n");
        html.append("<script src=\"/app.js\"></script>\n</body>\n</html>\n");
        return html.toString();
    }

    private static String buildSidebar() {
        StringBuilder html = new StringBuilder();
        html.append("<aside class=\"sidebar\">\n");
        html.append("<div class=\"brand\"><span class=\"logo\">D</span><span class=\"brand-name\">DNO</span></div>\n");
        html.append("<div class=\"caption\">DISTRIBUTION NETWORK<br>OPTIMIZER</div>\n");
        html.append("<nav class=\"nav\">\n");
        for (int i = 0; i < names.length; i++) {
            String css = "";
            if (names[i].equals("Optimization")) {
                css = " class=\"active\"";
            }
            html.append("<a href=\"#\"" + css + "><span class=\"icon\">" + icons[i] + "</span>" + names[i] + "</a>\n");
        }
        html.append("</nav>\n");
        html.append("<div class=\"user\"><span class=\"avatar\">IS</span><div><div class=\"user-name\">Demo User</div>");
        html.append("<div class=\"user-role\">Manager Workspace</div></div></div>\n");
        html.append("</aside>\n");
        return html.toString();
    }

    private static String buildReviewCard(OptimizationRun run) {
        String validated = "Check needed";
        if (run.isValidated()) {
            validated = "Validated";
        }
        boolean acceptDisabled = !(run.isPending() && run.isValidated());

        StringBuilder html = new StringBuilder();
        html.append("<section class=\"card\">\n");
        html.append("<h2>Recommendations</h2>\n");
        html.append("<p class=\"card-info\">Examine recommended fulfillment warehouses before taking a decision.</p>\n");
        html.append("<div class=\"stats\">\n");
        html.append(buildStat("Candidate warehouses", String.valueOf(run.getWarehouseCount()), "Active locations"));
        html.append(buildStat("Recommended", String.valueOf(run.getRecommendedPlans()), "Best feasible match"));
        html.append(buildStat("Stock / capacity", validated, "Before approval"));
        html.append(buildStat("Decision", run.getDecision(), "Manager acceptance"));
        html.append("</div>\n");

        html.append("<table>\n<thead>\n<tr><th>Order</th><th>Warehouse</th><th>Coverage</th><th>Cost</th><th>Result</th></tr>\n</thead>\n<tbody>\n");
        List<Recommendation> list = run.getRecommendations();
        for (Recommendation r : list) {
            html.append("<tr>");
            html.append("<td>" + r.getOrderId() + "</td>");
            html.append("<td>" + r.getWarehouse() + "</td>");
            html.append("<td>" + r.getCoverage() + "%</td>");
            html.append("<td>" + euro(r.getCost()) + "</td>");
            html.append("<td>" + r.getResult() + "</td>");
            html.append("</tr>\n");
        }
        html.append("</tbody>\n</table>\n");

        html.append("<div class=\"bottom\">\n");
        html.append("<div class=\"notice\"><strong>Review required</strong>");
        html.append("<span>A manager must approve a recommendation before warehouse assignments or capacity reservations are applied.</span></div>\n");
        html.append("<div class=\"buttons\">\n");
        html.append("<button type=\"button\" class=\"btn\" onclick=\"alert('Back to optimization results.')\">Back</button>\n");
        html.append("<button type=\"button\" class=\"btn dark\"" + disabledAttr(acceptDisabled)
                + " onclick=\"showDialog('acceptDialog', 'Approve the selected recommendations and create warehouse assignments.', 'US 6.2 • SAMPLE DATA')\">Accept All Recommendations</button>\n");
        html.append("</div>\n</div>\n");
        html.append("</section>\n");
        return html.toString();
    }

    private static String disabledAttr(boolean disabled) {
        if (disabled) {
            return " disabled";
        }
        return "";
    }

    private static String buildStat(String caption, String value, String hint) {
        return "<div class=\"stat\"><div class=\"stat-caption\">" + caption + "</div><div class=\"stat-value\">"
                + value + "</div><div class=\"stat-hint\">" + hint + "</div></div>\n";
    }

    private static String buildAcceptDialog(OptimizationRun run) {
        String[][] rows = {
                {"Optimization run", run.getId()},
                {"Orders included", String.valueOf(run.getOrdersOptimized())},
                {"Baseline cost", euro(run.getBaselineCost())},
                {"Optimized cost", euro(run.getOptimizedCost())}
        };
        return buildDialog("acceptDialog", "Accept " + run.getOrdersOptimized() + " recommendations?",
                "These assignments will become active and be sent to their<br>destination warehouses.",
                rows, "Go Back", "Accept &amp; Assign", "/accept");
    }

    private static String buildDialog(String id, String title, String text, String[][] rows,
            String backText, String confirmText, String action) {
        StringBuilder html = new StringBuilder();
        html.append("<form class=\"dialog\" id=\"" + id + "\" method=\"post\" action=\"" + action + "\">\n");
        html.append("<h2>" + title + "</h2>\n");
        html.append("<p>" + text + "</p>\n");
        html.append("<div class=\"rows\">\n");
        for (String[] row : rows) {
            html.append("<div class=\"row\"><span>" + row[0] + "</span><strong>" + row[1] + "</strong></div>\n");
        }
        html.append("</div>\n");
        html.append("<div class=\"dialog-buttons\">\n");
        html.append("<button type=\"button\" class=\"btn\" onclick=\"closeDialog()\">" + backText + "</button>\n");
        html.append("<button type=\"submit\" class=\"btn dark\">" + confirmText + "</button>\n");
        html.append("</div>\n</form>\n");
        return html.toString();
    }
}
