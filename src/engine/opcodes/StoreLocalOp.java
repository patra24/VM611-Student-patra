package engine.opcodes;

import java.util.Map;
import java.util.Stack;

import engine.StackFrame;

/**
 * Stores a value from the stack into a local variable.
 */
public class StoreLocalOp implements Opcode {
    private String varName;

    public StoreLocalOp(String varName) {
        this.varName = varName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        Map<String, Integer> localVars = callStack.peek().getLocalVars();
        localVars.put(varName, opStack.pop());
    }
}
