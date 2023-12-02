package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Store a value from the stack into a field.
 */
public class StoreFieldOp extends Opcode {
    private String fieldName;

    public StoreFieldOp(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public String toString() {
        return "store_field " + fieldName;
    }

}
