package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.heap.Heap;

/**
 * Creates an object, and puts its id on the stack.
 */
public class NewObjectOp extends Opcode {
    private String className;

    public NewObjectOp(String className) {
        this.className = className;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
    }

    @Override
    public String toString() {
        return "new_object " + className;
    }

}
