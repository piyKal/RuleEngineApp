package org.piyush;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RuleDatabase {

        private static final String URL = "jdbc:postgresql://localhost:5432/rule_engine_db";
        private static final String USER = "your_username";
        private static final String PASSWORD = "your_password";

        public int saveRule(String ruleString) throws SQLException {
            String query = "INSERT INTO rules (rule_string) VALUES (?) RETURNING rule_id";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, ruleString);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("rule_id");
                }
            }
            return -1;
        }

        public List<Rule> getAllRules() throws SQLException {
            String query = "SELECT * FROM rules";
            List<Rule> rules = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Rule rule = new Rule(rs.getInt("rule_id"), rs.getString("rule_string"));
                    rules.add(rule);
                }
            }
            return rules;
        }
}
