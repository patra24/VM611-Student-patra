package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Possibly branches to another location in the code.
 */
public class BranchOp implements Opcode {
    public enum Type {
        UNCONDITIONAL,
        TRUE,
        FALSE
    }

    private Type type;
    private int dest;

    public BranchOp(Type type, int dest) {
        this.type = type;
        this.dest = dest;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        if (type == Type.UNCONDITIONAL ||
        // Only one of these pops will happen, because of short-circuiting.
            (type == Type.TRUE && opStack.pop() == 1) ||
            (type == Type.FALSE && opStack.pop() != 1)) {
            return dest;
        }

        return pc + 1;
    }
}
