package org.piyush;

public class Rule {
        private final int ruleId;
        private final String ruleString;

        public Rule(int ruleId, String ruleString) {
            this.ruleId = ruleId;
            this.ruleString = ruleString;
        }

        public int getRuleId() {
            return ruleId;
        }

        public String getRuleString() {
            return ruleString;
        }


}
