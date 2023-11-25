package engine.opcodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import engine.CompiledClassCache;
import engine.Method;
import engine.StackFrame;

/**
 * Calls a method.
 */
public class CallOp implements Opcode {
    private String methodName;

    public CallOp(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Stack<Integer> opStack) {

        Method method = CompiledClassCache.instance().resolveMethod(methodName);

        // Create a new locals map, and populate it with the method arguments.
        Map<String, Integer> locals = new HashMap<>();
        List<String> params = method.getParameters();
        // Parameters are on the stack in reverse order.
        for (int i = params.size() - 1; i >= 0; i--) {
            String param = params.get(i);
            Integer value = opStack.pop();
            locals.put(param, value);
        }

        // Create the new stack frame. pc will default to zero.
        StackFrame newFrame = new StackFrame(method.getOpcodes(), locals);
        callStack.push(newFrame);
    }

}
