package org.piyush;
import java.util.Map;
import java.util.Stack;


public class RuleEngineService {

        public static Node createRule(String ruleString) {
            Stack<Node> stack = new Stack<>();
            String[] tokens = ruleString.split(" ");

            for (String token : tokens) {
                switch (token) {
                    case "AND":
                    case "OR":
                        Node operator = new Node("operator", token);
                        operator.right = stack.pop();
                        operator.left = stack.pop();
                        stack.push(operator);
                        break;
                    default:
                        stack.push(new Node("operand", token));
                        break;
                }
            }

            return stack.isEmpty() ? null : stack.pop();
        }

        public static boolean evaluateRule(Node node, Map<String, Object> data) {
            if (node == null) return false;

            if ("operator".equals(node.type)) {
                if ("AND".equals(node.value)) {
                    return evaluateRule(node.left, data) && evaluateRule(node.right, data);
                } else if ("OR".equals(node.value)) {
                    return evaluateRule(node.left, data) || evaluateRule(node.right, data);
                }
            } else if ("operand".equals(node.type)) {
                return evaluateOperand(node.value, data);
            }
            return false;
        }

    private static boolean evaluateOperand(String condition, Map<String, Object> data) {
        // Trim whitespace and remove surrounding parentheses if necessary
        condition = condition.trim();
        if (condition.startsWith("(") && condition.endsWith(")")) {
            condition = condition.substring(1, condition.length() - 1).trim();
        }

        // Split based on space; account for conditions with no spaces around operators
        String[] parts = condition.split("(?<=\\S) (?=\\S)|(?<=\\S) (?=\\()|(?<=\\)) (?=\\S)");

        // Check if parts have enough elements
        if (parts.length < 3) {
            System.err.println("Invalid condition format: " + condition);
            return false; // or throw an exception
        }

        String attribute = parts[0].trim();
        String operator = parts[1].trim();
        String valueStr = parts[2].trim().replace("'", ""); // Remove quotes for comparison

        // Attempt to parse the value; it could be a number or a string
        Object value;
        if (valueStr.matches("\\d+")) { // Check if it's a number
            value = Integer.parseInt(valueStr);
        } else {
            value = valueStr; // Treat it as a string if it's not a number
        }

        Object dataValue = data.get(attribute);
        if (dataValue == null) {
            System.err.println("Attribute not found: " + attribute);
            return false;
        }

        // Compare the values based on operator type
        switch (operator) {
            case ">":
                return (int) dataValue > (int) value;
            case "<":
                return (int) dataValue < (int) value;
            case "=":
                return dataValue.toString().equals(value.toString());
            default:
                System.err.println("Invalid operator: " + operator);
                return false;
        }
    }


}




