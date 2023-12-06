package engine.heap;

import types.DataType;

/**
 * HeapArray represents an array on the heap.
 */
public class HeapArray extends HeapEntry {
    private DataType type;
    private int[] data;

    /**
     * Creates an array. This is always a one-dimensional array. Multiple HeapArrays
     * must be created to represent multi-dimenional arrays.
     *
     * @param id     the heap id
     * @param type   the array type
     * @param length the array length
     */
    public HeapArray(int id, DataType type, int length) {
        super(id);
        this.type = type;
        this.data = new int[length];
    }

    /**
     * Returns the value an an index.
     *
     * @param index the index
     * @return the value
     */
    public int getAt(int index) {
        return data[index];
    }

    /**
     * Sets the value at an index.
     *
     * @param index the index
     * @param value the value
     */
    public void setAt(int index, int value) {
        data[index] = value;
    }

    /**
     * Returns the length of the array.
     *
     * @return the length of the array
     */
    public int getLength() {
        return data.length;
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public int getFieldValue(String name) {
        if ("length".equals(name)) {
            return data.length;
        }
        throw new RuntimeException("Arrays do not have field: " + name);
    }
}
