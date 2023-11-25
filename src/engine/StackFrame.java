package engine;

import java.util.List;
import java.util.Map;

import engine.opcodes.Opcode;

/**
 * A frame in the call stack.
 */
public class StackFrame {
    /** Index of next instruction to be executed */
    private int programCounter;
    /** The opcodes for this method */
    private List<Opcode> opcodes;
    /** The local vars for this method */
    private Map<String, Integer> localVars;

    public StackFrame(List<Opcode> opcodes, Map<String, Integer> localVars) {
        this.opcodes = opcodes;
        this.localVars = localVars;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Moves the program counter forward one opcode.
     */
    public void advanceProgramCounter() {
        programCounter++;
    }

    /**
     * Sets the program counter.
     * 
     * @param programCounter the new value
     */
    public void jumpTo(int programCounter) {
        this.programCounter = programCounter;
    }

    /**
     * Returns the next opcode.
     * 
     * @return the next opcode, or null
     */
    public Opcode getNextOpcode() {
        if (programCounter == opcodes.size()) {
            return null;
        }
        return opcodes.get(programCounter);
    }

    /**
     * Returns the local variables
     * 
     * @return the local variables
     */
    public Map<String, Integer> getLocalVars() {
        return localVars;
    }

    /**
     * Returns true if the method is complete
     * 
     * @return true if the method is complete
     */
    public boolean isComplete() {
        return programCounter == opcodes.size();
    }
}
