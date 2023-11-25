package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import engine.opcodes.Opcode;

public class VMThread {
    List<Opcode> opcodes;

    public VMThread(String entryPointName) {
        Method method = CompiledClassCache.instance().resolveMethod(entryPointName);
        opcodes = method.getOpcodes();
    }

    public Map<String, Integer> run() {
        Stack<StackFrame> callStack = new Stack<>();
        Map<String, Integer> locals = new HashMap<>();
        callStack.push(new StackFrame(opcodes, locals));

        Stack<Integer> opStack = new Stack<>();

        while (!callStack.peek().isComplete()) {
            StackFrame frame = callStack.peek();
            Opcode op = frame.getNextOpcode();

            int pcBefore = frame.getProgramCounter();
            op.execute(callStack, opStack);
            if (frame.getProgramCounter() == pcBefore) {
                frame.advanceProgramCounter();
            }
        }

        return locals;
    }
}
