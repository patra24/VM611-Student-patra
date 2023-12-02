package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Loads an field onto the stack.
 */
public class LoadFieldOp extends Opcode {
    private String fieldName;

    public LoadFieldOp(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Opcode convertToStore() {
        return new StoreFieldOp(fieldName);
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public String toString() {
        return "load_field " + fieldName;
    }

}
