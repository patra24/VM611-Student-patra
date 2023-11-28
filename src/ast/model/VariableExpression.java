package ast.model;

/**
 * Represents a variable name.
 */
public class VariableExpression extends Expression {
    private String name;

    public VariableExpression(String aName) {
        this.name = aName;
    }

    public String getName() {
        return name;
    }

}
