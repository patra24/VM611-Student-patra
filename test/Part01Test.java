import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Part01Test extends TestBase {

    /**
     * Test load_const, store_local.
     */
    @Test
    public void testConstStore() {
        /** #score(5) */
        hintContext = "load_const, store_local";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 42, vars.get("a"));

        vars = TestUtil.testCode("""
            load_const 12
            load_const 23
            store_local a
            store_local b
            """);

        assertEquals(hint("var had wrong value"), (Integer) 23, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 12, vars.get("b"));
    }

    /**
     * Test load_local.
     */
    @Test
    public void testLoadLocal() {
        /** #score(5) */
        hintContext = "load_local";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            load_const 13
            store_local d
            store_local c
            load_local d
            store_local b
            load_local c
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 42, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 13, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 42, vars.get("c"));
        assertEquals(hint("var had wrong value"), (Integer) 13, vars.get("d"));
    }

    /**
     * Test add.
     */
    @Test
    public void testAdd() {
        /** #score(1) */
        hintContext = "add";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            load_const 13
            add
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 55, vars.get("a"));

        vars = new HashMap<>();
        vars = TestUtil.testCode("""
            load_const 42
            load_const 13
            load_const 9
            add
            add
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 64, vars.get("a"));
    }

    /**
     * Test sub.
     */
    @Test
    public void testSub() {
        /** #score(1) */
        hintContext = "sub";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            load_const 13
            sub
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 29, vars.get("a"));
    }

    /**
     * Test mul.
     */
    @Test
    public void testMul() {
        /** #score(1) */
        hintContext = "mul";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 4
            load_const 13
            mul
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 52, vars.get("a"));
    }

    /**
     * Test div.
     */
    @Test
    public void testDiv() {
        /** #score(1) */
        hintContext = "div";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            load_const 7
            div
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 6, vars.get("a"));
    }

    /**
     * Test mod.
     */
    @Test
    public void testMod() {
        /** #score(1) */
        hintContext = "mod";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 42
            load_const 5
            mod
            store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer)2, vars.get("a"));
    }

    /**
     * Test eq.
     */
    @Test
    public void testEq() {
        /** #score(1) */
        hintContext = "cmpEQ";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpEQ
            store_local a
            load_const 10
            load_const 10
            cmpEQ
            store_local b
            load_const 11
            load_const 10
            cmpEQ
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("c"));
    }

    /**
     * Test ne.
     */
    @Test
    public void testNe() {
        /** #score(1) */
        hintContext = "cmpNEQ";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpNEQ
            store_local a
            load_const 10
            load_const 10
            cmpNEQ
            store_local b
            load_const 11
            load_const 10
            cmpNEQ
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("c"));
    }

    /**
     * Test lt.
     */
    @Test
    public void testLt() {
        /** #score(1) */
        hintContext = "cmpLT";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpLT
            store_local a
            load_const 10
            load_const 10
            cmpLT
            store_local b
            load_const 11
            load_const 10
            cmpLT
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("c"));
    }

    /**
     * Test lte.
     */
    @Test
    public void testLte() {
        /** #score(1) */
        hintContext = "cmpLTE";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpLTE
            store_local a
            load_const 10
            load_const 10
            cmpLTE
            store_local b
            load_const 11
            load_const 10
            cmpLTE
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("c"));
    }

    /**
     * Test gt.
     */
    @Test
    public void testGt() {
        /** #score(1) */
        hintContext = "cmpGT";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpGT
            store_local a
            load_const 10
            load_const 10
            cmpGT
            store_local b
            load_const 11
            load_const 10
            cmpGT
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("c"));
    }

    /**
     * Test gte.
     */
    @Test
    public void testGte() {
        /** #score(1) */
        hintContext = "cmpGTE";
        Map<String, Integer> vars = TestUtil.testCode("""
            load_const 9
            load_const 10
            cmpGTE
            store_local a
            load_const 10
            load_const 10
            cmpGTE
            store_local b
            load_const 11
            load_const 10
            cmpGTE
            store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer) 0, vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer) 1, vars.get("c"));
    }

    /**
     * Test branch.
     */
    @Test
    public void testBranch() {
        /** #score(4) */
        hintContext = "branch";
        Map<String, Integer> vars = TestUtil.testCode("""
            0: load_const 12
            1: branch 3
            2: load_const 23
            3: store_local a
            """);

        assertEquals(hint("var had wrong value"), (Integer) 12, vars.get("a"));
    }

    /**
     * Test branchT.
     */
    @Test
    public void testBranchT() {
        /** #score(4) */
        hintContext = "branchT";
        Map<String, Integer> vars = TestUtil.testCode("""
            0: load_const 0
            1: branchT 4
            2: load_const 1
            3: store_local a
            4: load_const 1
            5: branchT 8
            6: load_const 1
            7: store_local b
            8: load_const 1
            9: store_local c
            """);

        assertEquals(hint("var had wrong value"), (Integer)1, vars.get("a"));
        assertNull(hint("var had wrong value"), vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer)1, vars.get("c"));
    }

    /**
     * Test branchF.
     */
    @Test
    public void testBranchF() {
        /** #score(4) */
        hintContext = "branchF";
        Map<String, Integer> vars = TestUtil.testCode("""
            0: load_const 0
            1: branchF 4
            2: load_const 1
            3: store_local a
            4: load_const 1
            5: branchF 8
            6: load_const 1
            7: store_local b
            8: load_const 1
            9: store_local c
            """);

        assertNull(hint("var had wrong value"),  vars.get("a"));
        assertEquals(hint("var had wrong value"), (Integer)1, vars.get("b"));
        assertEquals(hint("var had wrong value"), (Integer)1, vars.get("c"));
    }

    /**
     * Test loop.
     */
    @Test
    public void testLoop() {
        /** #score(2) */
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
}
