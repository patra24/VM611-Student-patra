package engine.heap;

import java.util.HashMap;
import java.util.Map;

import types.Clazz;
import types.DataType;

/**
 * HeapObject represents an object on the heap.
 */
public class HeapObject extends HeapEntry {
    private Clazz clazz;
    private Map<String, Integer> fieldValues;

    /**
     * Creates a HeapObject.
     *
     * @param id    the heap id
     * @param clazz the clazz
     */
    public HeapObject(int id, Clazz clazz) {
        super(id);
        this.clazz = clazz;
        this.fieldValues = new HashMap<>();
    }

    @Override
    public DataType getType() {
        return clazz.getType();
    }

    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public int getFieldValue(String name) {
        return fieldValues.get(name);
    }

    public void setFieldValue(String name, int value) {
        fieldValues.put(name, value);
    }

}
