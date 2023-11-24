package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import engine.opcodes.Opcode;

public class VMThread {
    List<Opcode> opcodes;

    public VMThread(List<Opcode> opcodes) {
        this.opcodes = opcodes;
    }

    public Map<String, Integer> run() {
        Stack<Integer> opStack = new Stack<>();
        Map<String, Integer> locals = new HashMap<>();

        int pc = 0;
        while (pc < opcodes.size()) {
            Opcode op = opcodes.get(pc);
            pc = op.execute(pc, opStack, locals);
        }

        return locals;
    }
}
