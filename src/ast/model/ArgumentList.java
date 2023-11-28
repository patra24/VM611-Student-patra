package ast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the argument list in a method call.
 */
public class ArgumentList extends ArrayList<Expression> {

    public ArgumentList(List<Expression> arguments) {
        super(arguments);
    }

}
