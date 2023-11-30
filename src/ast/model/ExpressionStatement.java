package ast.model;

import ast.visitors.Visitor;

/**
 * Represents a statement that is itself an expression, e.g. a method call.
 */
public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement(Expression expr) {
        this.expression = expr;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor v) {

    }

}
