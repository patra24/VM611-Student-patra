package engine.heap;

import java.util.Map;

import types.Clazz;
import types.DataType;

/**
 * HeapObject represents an object on the heap.
 */
public class HeapObject {
    private int id;
    private Clazz clazz;
    private Map<String, Integer> fieldValues;

    /**
     * Creates a HeapObject.
     *
     * @param id    the heap id
     * @param clazz the clazz
     */
    public HeapObject(int id, Clazz clazz) {
    }

    public int getId() {
        return id;
    }

    public DataType getType() {
        return clazz.getType();
    }

    public Clazz getClazz() {
        return clazz;
    }

    public int getFieldValue(String name) {
        return 0;
    }

    public void setFieldValue(String name, int value) {
    }

    @Override
    public String toString() {
        return clazz.getType() + "@" + id;
    }

}
