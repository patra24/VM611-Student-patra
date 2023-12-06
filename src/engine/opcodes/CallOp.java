package engine.opcodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import ast.model.ParameterDefinition;
import engine.CompiledClassCache;
import engine.StackFrame;
import engine.heap.Heap;
import engine.heap.HeapObject;
import types.Method;

/**
 * Calls a method.
 */
public class CallOp extends Opcode {
    private String methodName;

    public CallOp(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void execute(Stack<StackFrame> callStack, Heap heap, Stack<Integer> opStack) {
        int objId = opStack.pop();
        HeapObject obj = (HeapObject) heap.getEntry(objId);
        Method method = CompiledClassCache.instance().resolveMethod(obj.getClazz().getName(), methodName);

        // Create a new locals map, and populate it with the method arguments.
        Map<String, Integer> locals = new HashMap<>();
        locals.put("this", objId);
        List<ParameterDefinition> params = method.getParameters();
        // Parameters are on the stack in reverse order.
        for (int i = params.size() - 1; i >= 0; i--) {
            ParameterDefinition param = params.get(i);
            Integer value = opStack.pop();
            locals.put(param.getName(), value);
        }

        // Create the new stack frame. pc will default to zero.
        StackFrame newFrame = new StackFrame(method.getOpcodes(), locals);
        callStack.push(newFrame);
    }

    @Override
    public String toString() {
        return "call " + methodName;
    }

}
