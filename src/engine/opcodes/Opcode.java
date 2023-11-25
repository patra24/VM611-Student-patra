package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;

public interface Opcode {
    /**
     * Executes the opcode.
     *
     * @param callStack the call stack
     * @param opStack   the op stack
     */
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack);
}
