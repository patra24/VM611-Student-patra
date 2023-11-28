package ast.model;

import types.DataType;

/**
 * Represents a method definition.
 */
public class MethodDefinition {
    private String name;
    private DataType returnType;
    private ParameterList parameters;
    private CompoundStatement body;

    public MethodDefinition(DataType returnType, String name, ParameterList parameters,
        CompoundStatement body) {

        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public ParameterList getParameters() {
        return parameters;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public CompoundStatement getBody() {
        return body;
    }

}
