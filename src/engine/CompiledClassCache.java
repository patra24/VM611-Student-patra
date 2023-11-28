package engine;

import java.util.HashMap;
import java.util.Map;

import types.Method;

/**
 * Singleton cache for compiled code.
 */
public class CompiledClassCache {
    private static CompiledClassCache instance;

    public static CompiledClassCache instance() {
        if (instance == null) {
            instance = new CompiledClassCache();
        }

        return instance;
    }

    private Map<String, Method> methods;

    public CompiledClassCache() {
        methods = new HashMap<>();
    }

    /**
     * Caches a method definition.
     *
     * @param methodName the method name
     * @param code       the opcodes
     */
    public void saveMethod(Method method) {
        methods.put(method.getName(), method);
    }

    /**
     * Resolves a method definition.
     *
     * @param methodName the method name
     * @return the opcodes
     */
    public Method resolveMethod(String methodName) {
        return methods.get(methodName);
    }
}
