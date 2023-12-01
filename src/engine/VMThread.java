package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import engine.VMThreadState.State;
import engine.opcodes.Opcode;
import types.Method;

/**
 * A thread of execution in the VM.
 */
public class VMThread {
    /** The method that will be executed when the thread starts */
    private Method entryPoint;
    /** The local variables for the entry point */
    private Map<String, Integer> entryPointLocals;
    /** The thread's priority */
    private int priority;
    private Stack<StackFrame> callStack;
    private Stack<Integer> opStack;

    public VMThread(String entryPointName) {
        entryPoint = CompiledClassCache.instance().resolveMethod(entryPointName);
        entryPointLocals = new HashMap<>();
        callStack = new Stack<>();
        callStack.push(new StackFrame(entryPoint.getOpcodes(), entryPointLocals));
        opStack = new Stack<>();
    }

    public VMThread(String entryPointName, int priority) {
        this(entryPointName);
        this.priority = priority;
    }

    /**
     * Runs one instruction, and returns the thread's state.
     *
     * @return the thread's state
     */
    public VMThreadState runInstruction() {
        StackFrame frame = callStack.peek();

        Opcode op = frame.getNextOpcode();
        if (op == null) {
            throw new RuntimeException("No instructions left to run");
        }

        int pc = frame.getProgramCounter();
        VMThreadState state = op.executeWithState(callStack, opStack);
        // If the op wasn't a branch that updated pc, go to the next instruction.
        if (frame.getProgramCounter() == pc) {
            frame.advanceProgramCounter();
        }

        if (callStack.isEmpty() || callStack.peek().isComplete() && callStack.size() == 1) {
            return new VMThreadState(State.Complete, 0);
        }
        return state;
    }

    /**
     * Runs the thread, and returns the local variables from the entry point.
     * 
     * @return the locals from the entry point
     */
    public Map<String, Integer> run() {
        VMThreadState state;
        do {
            state = runInstruction();
        } while (state.getState() != State.Complete);

        return entryPointLocals;
    }

    /**
     * Returns the locals from the entry point.
     * 
     * @return the locals from the entry point
     */
    public Map<String, Integer> getEntryPointLocals() {
        return entryPointLocals;
    }

    public int getPriority() {
        return priority;
    }
}
