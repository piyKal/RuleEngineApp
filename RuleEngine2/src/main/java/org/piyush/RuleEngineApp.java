package org.piyush;
import java.util.HashMap;
import java.util.Map;

public class RuleEngineApp {
    public static void main(String[] args) {
        RuleEngineService ruleEngineService = new RuleEngineService();

        // Properly formatted rule string
        String ruleString = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";

        // Create the rule AST
        Node rule = ruleEngineService.createRule(ruleString);

        // Sample user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        userData.put("department", "Sales");
        userData.put("salary", 60000);
        userData.put("experience", 3);

        // Evaluate the rule
        boolean isEligible = ruleEngineService.evaluateRule(rule, userData);
        System.out.println("User eligibility: " + isEligible);
    }
}
