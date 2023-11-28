package ast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parameter list, e.g. "(int x, MyClass y)".
 */
public class ParameterList extends ArrayList<ParameterDefinition> {

    public ParameterList(List<ParameterDefinition> parameters) {
        super(parameters);
    }

}
