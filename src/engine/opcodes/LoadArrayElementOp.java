package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Loads an array element onto the stack.
 */
public class LoadArrayElementOp extends Opcode {

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public Opcode convertToStore() {
        return new StoreArrayElementOp();
    }

    @Override
    public String toString() {
        return "load_array_element";
    }

}
