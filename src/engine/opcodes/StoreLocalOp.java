package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Stores a value from the stack into a local variable.
 */
public class StoreLocalOp implements Opcode {
    private String varName;

    public StoreLocalOp(String varName) {
        this.varName = varName;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        localVars.put(varName, opStack.pop());
        return pc + 1;
    }
}
