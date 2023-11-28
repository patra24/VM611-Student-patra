package ast.model;

/**
 * Represents an assign statement.
 */
public class AssignStatement extends Statement {
    private Expression lValue;
    private Expression value;

    /**
     * Creates an assign statement.
     *
     * @param lvalue the variable
     * @param value  the value
     */
    public AssignStatement(Expression lvalue, Expression value) {
        this.lValue = lvalue;
        this.value = value;
    }

    public Expression getLValue() {
        return lValue;
    }

    public Expression getValue() {
        return value;
    }

}
