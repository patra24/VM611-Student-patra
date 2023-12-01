package engine.opcodes;

import java.util.Stack;

import engine.StackFrame;
import engine.VMThreadState;
import engine.VMThreadState.State;

/**
 * Causes the current thread to give up its turn.
 */
public class YieldOp extends Opcode {

    @Override
    public VMThreadState executeWithState(Stack<StackFrame> callStack, Stack<Integer> opStack) {
        return new VMThreadState(State.Yielded, 0);
    }

}
