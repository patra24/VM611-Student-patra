package engine.heap;

import java.util.HashMap;
import java.util.Map;

import types.Clazz;
import types.DataType;
import types.Value;

/**
 * HeapObject represents an object on the heap.
 */
public class HeapObject extends HeapEntry {
    private Clazz clazz;
    private Map<String, Value> fieldValues;

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
    public Value getFieldValue(String name) {
        return fieldValues.get(name);
    }

    public void setFieldValue(String name, Value value) {
        fieldValues.put(name, value);
    }

}
