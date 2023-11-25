package engine;

import java.util.List;
import java.util.Map;

import engine.opcodes.Opcode;

public class VMThread {
    List<Opcode> opcodes;

    public VMThread(String entryPointName) {
        Method method = CompiledClassCache.instance().resolveMethod(entryPointName);
        opcodes = method.getOpcodes();
    }

    public Map<String, Integer> run() {
        return null;
    }
}
