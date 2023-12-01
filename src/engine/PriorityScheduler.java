package engine;

import java.util.List;

/**
 * Scheduler that runs the highest priority threads first.
 */
public class PriorityScheduler {
    /** Current time. */
    private int currentTime;

    /**
     * Creates a CoopScheduler.
     *
     * @param list the list of threads to be executed
     */
    public PriorityScheduler(List<VMThread> list) {
        currentTime = 0;
    }

    /**
     * Returns the current thread.
     *
     * @return the current thread
     */
    public VMThread getCurrentThread() {
        return null;
    }

    /**
     * Executes the given number of instructions.
     *
     * @param numInstructions the number of instructions
     */
    public void run(int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            currentTime++;
            tick();
        }
    }

    /**
     * Executes one instruction.
     */
    private void tick() {
    }

}
