package ast.model;

import java.util.List;

/**
 * Represents a compound statement.
 */
public class CompoundStatement extends Statement {
    private List<Statement> body;

    public CompoundStatement(List<Statement> body) {
        this.body = body;
    }

    public List<Statement> getBody() {
        return body;
    }

}
