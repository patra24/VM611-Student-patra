package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;

public abstract class Opcode {
    /**
     * Executes the opcode.
     *
     * @param callStack the call stack
     * @param opStack   the op stack
     */
    public abstract void execute(Stack<StackFrame> callStack, Stack<Integer> opStack);

    /**
     * Converts this load_ opcode into an equivalent store_ operation. Not supported
     * for most opcodes.
     *
     * @return the store op
     */
    public Opcode convertToStore() {
        throw new RuntimeException(this.getClass().getSimpleName() + " can't be converted to a store opcode");
    }
}
