package engine;

import java.util.List;

import engine.opcodes.Opcode;

/**
 * Represents a compiled method.
 */
public class Method {
    private String name;
    private List<Opcode> opcodes;
    private List<String> parameters;

    public Method(String name, List<String> parameters, List<Opcode> opcodes) {
        this.name = name;
        this.parameters = parameters;
        this.opcodes = opcodes;
    }

    public String getName() {
        return name;
    }

    public List<Opcode> getOpcodes() {
        return opcodes;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
