package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;
import engine.heap.HeapArray;
import types.Value;

/**
 * Stores a value from the stack into an array element.
 */
public class StoreArrayElementOp extends Opcode {

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Value> opStack) {
        int arrayId = opStack.pop();
        HeapArray arr = (HeapArray) heap.getEntry(arrayId);
        int index = opStack.pop();
        int value = opStack.pop();
        arr.setAt(index, value);
    }

    @Override
    public String toString() {
        return "store_array_element";
    }

}
