package engine.opcodes;

import java.util.Map;
import java.util.Stack;

import engine.StackFrame;

/**
 * Loads a local variable onto the stack.
 */
public class LoadLocalOp extends Opcode {
    private String varName;

    public LoadLocalOp(String varName) {
        this.varName = varName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        Map<String, Integer> localVars = callStack.peek().getLocalVars();
        opStack.push(localVars.get(varName));
    }

    @Override
    public Opcode convertToStore() {
        return new StoreLocalOp(varName);
    }

    @Override
    public String toString() {
        return "load_local " + varName;
    }
}
