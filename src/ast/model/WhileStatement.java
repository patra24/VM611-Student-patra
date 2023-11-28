package ast.model;

/**
 * Represents a while statement.
 */
public class WhileStatement extends Statement {
    private Expression condition;
    private Statement body;

    public WhileStatement(Expression aCondition,
        Statement aBody) {
        this.condition = aCondition;
        this.body = aBody;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

}
