package engine.heap;

import java.util.HashMap;
import java.util.Map;

import types.Clazz;
import types.DataType;

/**
 * Heap stores objects and arrays.
 */
public class Heap {
    /** The next id to be assigned. */
    private int nextId = 1;
    /** The heap entries, by id. */
    private Map<Integer, HeapEntry> entries = new HashMap<>();

    /**
     * Gets an entry by id.
     *
     * @param id the id
     * @return the entry
     */
    public HeapEntry getEntry(int id) {
        return entries.get(id);
    }

    /**
     * Creates a new object type.
     *
     * @param clazz the clazz of the new object
     * @return the new object
     */
    public HeapObject createObject(Clazz clazz) {
        HeapObject newObj = new HeapObject(nextId, clazz);
        entries.put(nextId, newObj);
        nextId++;
        return newObj;
    }

    /**
     * Creates a new array.
     *
     * @param arrType the type of the array
     * @param dims    the array dimensions
     * @return the new array
     */
    public HeapArray createArray(DataType arrType, int[] dims) {
        return createArray(arrType, dims, 0);
    }

    private HeapArray createArray(DataType arrType, int[] dims, int index) {
        HeapArray arr = createHeapArray(arrType, dims[index]);

        if (index == dims.length - 1) {
            return arr;
        }

        // Recursively create the subarrays.
        DataType subArrType = arrType.getElementType();
        for (int i = 0; i < dims[index]; i++) {
            HeapArray subArr = createArray(subArrType, dims, index + 1);
            arr.setAt(i, subArr.getId());
        }

        return arr;
    }

    private HeapArray createHeapArray(DataType type, int length) {
        HeapArray arr = new HeapArray(nextId, type, length);
        entries.put(nextId, arr);
        nextId++;
        return arr;
    }
}
