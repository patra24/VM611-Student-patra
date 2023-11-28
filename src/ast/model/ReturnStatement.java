package ast.model;

/**
 * Represents a return statement.
 */
public class ReturnStatement extends Statement {
    private Expression returnValue;

    public ReturnStatement(Expression returnValue) {
        this.returnValue = returnValue;
    }

    public Expression getReturnValue() {
        return returnValue;
    }

}
