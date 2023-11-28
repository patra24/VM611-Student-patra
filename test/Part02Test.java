import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import ast.model.ParameterDefinition;
import engine.CompiledClassCache;
import types.DataType;
import types.Method;

public class Part02Test extends TestBase {
    /**
     * Test old code still runs.
     */
    @Test
    public void testSimpleCodeStillWorks() {
        /** #score(5) */
        hintContext = "load_const, store_local";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 42, vars.get("a"));

        hintContext = "binary operations";
        vars = TestUtil.testCode("""
            load_const 12
            load_const 23
            add
            load_const 21
            sub
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 14, vars.get("a"));
    }

    /**
     * Test branching code still runs.
     */
    @Test
    public void testBranchingCodeStillWorks() {
        /** #score(5) */
        hintContext = "complicated example";
        Map<String, Integer> vars = TestUtil.testCode("""
            0: load_const 0
            1: store_local a
            2: load_const 0
            3: store_local b
            4: load_local b
            5: load_const 100
            6: cmpGT
            7: branchT 17
            8: load_local a
            9: load_local b
            10: add
            11: store_local a
            12: load_local b
            13: load_const 1
            14: add
            15: store_local b
            16: branch 4
            17: load_const 1
            18: store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 5050, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 101, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("c"));
    }

    @Test
    public void testCallAndReturn() {
        /** #score(5) */
        hintContext = "call a function and return";

        Method foo = new Method(
            "foo",
            List.of(),
            TestUtil.parseOpcodes("""
                load_const 5
                return
                """));
        CompiledClassCache.instance().saveMethod(foo);

        Map<String, Integer> vars = TestUtil.testCode("""
            call foo
            store_local a
            load_const 123
            call foo
            load_const 234
            call foo
            store_local b
            store_local c
            store_local d
            store_local e
            """);

        assertEquals(hint("local had wrong value"), (Integer) 5, vars.get("a"));
        assertEquals(hint("local had wrong value"), (Integer) 5, vars.get("b"));
        assertEquals(hint("local had wrong value"), (Integer) 234, vars.get("c"));
        assertEquals(hint("local had wrong value"), (Integer) 5, vars.get("d"));
        assertEquals(hint("local had wrong value"), (Integer) 123, vars.get("e"));
    }

    @Test
    public void testFuncWithArgs() {
        /** #score(5) */
        hintContext = "function call with arguments";

        Method addMethod = new Method(
            "sub",
            List.of(
                new ParameterDefinition("x", DataType.INT),
                new ParameterDefinition("y", DataType.INT)),
            TestUtil.parseOpcodes("""
                load_local x
                load_local y
                sub
                return
                """));
        CompiledClassCache.instance().saveMethod(addMethod);

        Method maxMethod = new Method(
            "max",
            List.of(
                new ParameterDefinition("x", DataType.INT),
                new ParameterDefinition("y", DataType.INT)),
            TestUtil.parseOpcodes("""
                0: load_local x
                1: load_local y
                2: cmpGT
                3: branchF 7
                4: load_local x
                5: return
                6: branch 9
                7: load_local y
                8: return
                """));
        CompiledClassCache.instance().saveMethod(maxMethod);

        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 33
            load_const 12
            call sub
            store_local x
            load_const 12
            load_const 33
            call max
            store_local y
            load_const 33
            load_const 12
            call max
            store_local z
            """);

        assertEquals(hint("local had wrong value"), (Integer) 21, vars.get("x"));
        assertEquals(hint("local had wrong value"), (Integer) 33, vars.get("y"));
        assertEquals(hint("local had wrong value"), (Integer) 33, vars.get("z"));
    }

    @Test
    public void testFuncNested() {
        /** #score(5) */
        hintContext = "function calling another function";

        Method addMethod = new Method(
            "add",
            List.of(
                new ParameterDefinition("x", DataType.INT),
                new ParameterDefinition("y", DataType.INT)),
            TestUtil.parseOpcodes("""
                load_local x
                load_local y
                add
                return
                """));
        CompiledClassCache.instance().saveMethod(addMethod);

        Method fooMethod = new Method(
            "foo",
            List.of(
                new ParameterDefinition("x", DataType.INT),
                new ParameterDefinition("y", DataType.INT)),
            TestUtil.parseOpcodes("""
                load_local x
                load_local y
                call add
                load_local y
                call add
                return
                """));
        CompiledClassCache.instance().saveMethod(fooMethod);

        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 12
            load_const 33
            call foo
            store_local x
            """);

        assertEquals(hint("local had wrong value"), (Integer) 78, vars.get("x"));
    }

    @Test
    public void testRecursion() {
        /** #score(5) */
        hintContext = "recursive function";

        Method factorialMethod = new Method(
            "factorial",
            List.of(new ParameterDefinition("x", DataType.INT)),
            TestUtil.parseOpcodes("""
                0: load_local x
                1: load_const 1
                2: cmpGT
                3: branchT 6
                4: load_const 1
                5: return
                6: load_local x
                7: load_local x
                8: load_const 1
                9: sub
                10: call factorial
                11: mul
                12: return
                """));
        CompiledClassCache.instance().saveMethod(factorialMethod);

        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 3
            call factorial
            load_const 4
            call factorial
            store_local y
            store_local x
            """);

        assertEquals(hint("local had wrong value"), (Integer) 6, vars.get("x"));
        assertEquals(hint("local had wrong value"), (Integer) 24, vars.get("y"));
    }

    @Test
    public void testRecursion2() {
        /** #score(5) */
        hintContext = "recursive function with multiple recursive calls";

        Method factorialMethod = new Method(
            "fib",
            List.of(new ParameterDefinition("x", DataType.INT)),
            TestUtil.parseOpcodes("""
                0: load_local x
                1: load_const 2
                2: cmpGT
                3: branchT 6
                4: load_const 1
                5: return
                6: load_local x
                7: load_const 2
                8: sub
                9: call fib
                10: load_local x
                11: load_const 1
                12: sub
                13: call fib
                14: add
                15: return
                """));
        CompiledClassCache.instance().saveMethod(factorialMethod);

        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 5
            call fib
            load_const 7
            call fib
            store_local y
            store_local x
            """);

        assertEquals(hint("local had wrong value"), (Integer) 5, vars.get("x"));
        assertEquals(hint("local had wrong value"), (Integer) 13, vars.get("y"));
    }
}
