package engine.heap;

import types.DataType;

/**
 * HeapEntry is the base class for objects on the heap.
 */
public abstract class HeapEntry {
    /** The heap id */
    private int id;

    public HeapEntry(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getType() + "@" + id;
    }

    /**
     * Returns the type of the object.
     *
     * @return the type of the object
     */
    public abstract DataType getType();

    /**
     * Returns the value of a field.
     *
     * @param name the field name
     * @return the value
     */
    public abstract int getFieldValue(String name);
}
