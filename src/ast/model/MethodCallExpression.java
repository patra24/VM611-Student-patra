package ast.model;

import ast.visitors.Visitor;

/**
 * Represents a function call.
 */
public class MethodCallExpression extends Expression {
    private String methodName;
    private ArgumentList arguments;

    public MethodCallExpression(String methodName, ArgumentList arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArgumentList getArguments() {
        return arguments;
    }

    @Override
    public void accept(Visitor v) {

    }

}
