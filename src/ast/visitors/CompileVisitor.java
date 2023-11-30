package ast.visitors;

import java.util.List;

import engine.opcodes.Opcode;

/**
 * Visitor that generates opcodes from the AST.
 */
public class CompileVisitor extends AbstractVisitor {

    public CompileVisitor() {
    }

    public List<Opcode> getOpcodes() {
        return null;
    }

}
