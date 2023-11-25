package engine.opcodes;

import java.util.Map;
import java.util.Stack;

import engine.StackFrame;

/**
 * Loads a local variable onto the stack.
 */
public class LoadLocalOp implements Opcode {
    private String varName;

    public LoadLocalOp(String varName) {
        this.varName = varName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        Map<String, Integer> localVars = callStack.peek().getLocalVars();
        opStack.push(localVars.get(varName));
    }
}
