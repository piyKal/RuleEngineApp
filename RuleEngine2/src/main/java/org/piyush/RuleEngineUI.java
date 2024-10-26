package org.piyush;
import java.util.HashMap;
import java.util.Map;

public class RuleEngineUI {
    public void display() {
        String ruleString = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"; // Properly formatted rule

        Node rule = RuleEngineService.createRule(ruleString);
        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        data.put("department", "Sales");
        data.put("salary", 60000);
        data.put("experience", 3); // Include all relevant attributes

        boolean isEligible = RuleEngineService.evaluateRule(rule, data);
        System.out.println("User eligibility: " + isEligible);
    }


}
