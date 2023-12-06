package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Stores a value from the stack into an array element.
 */
public class StoreArrayElementOp extends Opcode {

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public String toString() {
        return "store_array_element";
    }

}
