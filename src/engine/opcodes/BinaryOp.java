package engine.opcodes;

import java.util.Map;
import java.util.Stack;

/**
 * Performs an operation with the two values on the top of the stack.
 *
 */
public class BinaryOp implements Opcode {
    private Operator op;

    /**
     * Creates a BinaryOp.
     *
     * @param op the operator
     */
    public BinaryOp(Operator op) {
        this.op = op;
    }

    @Override
    public int execute(int pc, Stack<Integer> opStack, Map<String, Integer> localVars) {
        int op2 = opStack.pop();
        int op1 = opStack.pop();
        int result = op.apply(op1, op2);

        opStack.push(result);
        return pc + 1;
    }
}
