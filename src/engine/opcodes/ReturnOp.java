package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;

/**
 * Returns from a function call.
 */
public class ReturnOp implements Opcode {

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        callStack.pop();
    }

}
