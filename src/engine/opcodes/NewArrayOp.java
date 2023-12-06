package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Creates an array object, and puts its id on the stack.
 */
public class NewArrayOp extends Opcode {
    private String type;
    private int numDimensions;

    public NewArrayOp(String type, int numDimensions) {
        this.type = type;
        this.numDimensions = numDimensions;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public String toString() {
        return "new_array " + type + " " + numDimensions;
    }

}
