package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;

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
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        if (type == Type.UNCONDITIONAL ||
        // Only one of these pops will happen, because of short-circuiting.
            (type == Type.TRUE && opStack.pop() == 1) ||
            (type == Type.FALSE && opStack.pop() != 1)) {
            callStack.peek().jumpTo(dest);
        }
    }
}
