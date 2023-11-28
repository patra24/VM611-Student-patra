package ast.model;

import types.DataType;

/**
 * Represents a parameter definition, e.g. "int x".
 */
public class ParameterDefinition {
    private String name;
    private DataType type;

    public ParameterDefinition(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }
}
