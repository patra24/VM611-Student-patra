package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;
import engine.heap.HeapArray;
import types.Value;

/**
 * Loads an array element onto the stack.
 */
public class LoadArrayElementOp extends Opcode {

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Value> opStack) {
        int val = opStack.pop();
        HeapArray arr = (HeapArray) heap.getEntry(val);
        int index = opStack.pop();
        opStack.push(arr.getAt(index));
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
