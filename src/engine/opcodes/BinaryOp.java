package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;
import types.Value;

/**
 * Performs an operation with the two values on the top of the stack.
 *
 */
public class BinaryOp extends Opcode {
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
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Value> opStack) {
        int op2 = opStack.pop();
        int op1 = opStack.pop();
        int result = op.apply(op1, op2);

        opStack.push(result);
    }

    @Override
    public String toString() {
        return op.getInstruction();
    }
}
