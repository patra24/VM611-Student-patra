package engine.heap;

import java.util.HashMap;
import java.util.Map;

import types.Clazz;

/**
 * Heap stores objects and arrays.
 */
public class Heap {
    /** The next id to be assigned. */
    private int nextId = 1;
    /** The heap entries, by id. */
    private Map<Integer, HeapObject> entries = new HashMap<>();

    /**
     * Gets an entry by id.
     *
     * @param id the id
     * @return the entry
     */
    public HeapObject getEntry(int id) {
        return null;
    }

    /**
     * Creates a new object type.
     *
     * @param clazz the clazz of the new object
     * @return the new object
     */
    public HeapObject createObject(Clazz clazz) {
        return null;
    }
}
