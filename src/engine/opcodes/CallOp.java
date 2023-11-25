package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;

/**
 * Calls a method.
 */
public class CallOp implements Opcode {

    public CallOp(String methodName) {
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
    }
}
