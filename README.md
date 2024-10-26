
# Java Rule Engine with AST for User Eligibility Evaluation

This project is a Java-based Rule Engine designed to determine user eligibility based on custom-defined rules involving attributes such as age, department, salary, and experience. It leverages a 3-tier architecture with UI, API, and backend layers for modular and scalable rule management and evaluation.

## Features

- **Abstract Syntax Tree (AST) for Rules**: Converts rule strings into an AST structure for efficient rule parsing and evaluation.
- **Dynamic Rule Definition and Combination**: Allows creation, modification, and merging of complex rules.
- **Database Support**: Uses PostgreSQL for rule storage, providing a schema to manage application metadata and rule definitions.
- **API Endpoints**:
  - `create_rule(rule_string)`: Parses a rule string and returns an AST.
  - `combine_rules(rules)`: Combines multiple rules for efficient evaluation.
  - `evaluate_rule(data)`: Evaluates a rule based on user attributes.
- **Error Handling**: Includes validation and error management for invalid syntax, unsupported operations, and missing attributes.

## Prerequisites

- **Java 17** or higher
- **Maven** (for project setup and dependency management)
- **PostgreSQL** (or modify for another database)

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/java-rule-engine.git
cd java-rule-engine
```

### Set Up PostgreSQL

1. Create a PostgreSQL database and configure access.
2. Define the database schema as provided in the `schema.sql` file:

   ```sql
   CREATE TABLE rules (
       id SERIAL PRIMARY KEY,
       rule_text TEXT NOT NULL
   );
   ```

3. Update the database credentials in the project configuration file (`application.properties` or relevant section in code).

### Build and Run the Project

Use Maven to build and run the project:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.piyush.RuleEngineApp"
```

## Example Usage

Define rules as strings to be parsed and evaluated. The following example demonstrates rule creation and user data evaluation:

```java
String ruleString = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";
Map<String, Object> userData = Map.of("age", 35, "department", "Sales", "salary", 60000, "experience", 3);

Node ruleAST = ruleEngineService.createRule(ruleString);
boolean isEligible = ruleEngineService.evaluateRule(ruleAST, userData);

System.out.println("User eligibility: " + isEligible);
```

## Testing

Unit tests are included to validate core functionalities like rule creation, AST structure, rule combination, and evaluation:

```bash
mvn test
```

## Project Structure

- **src/main/java**: Core application files, including the rule engine service, AST classes, and UI.
- **src/test/java**: Test cases for rule creation, combination, and evaluation.
- **schema.sql**: Database schema file for PostgreSQL setup.
- **pom.xml**: Maven configuration file with dependencies.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides a clear project overview, installation guide, usage instructions, and setup details, perfect for GitHub! Let me know if you'd like any specific customization.
