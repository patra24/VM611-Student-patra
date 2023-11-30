import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ast.visitors.CompileVisitor;
import ast.visitors.PrintVisitor;
import ast.visitors.Visitable;
import engine.CompiledClassCache;
import engine.VMThread;
import engine.opcodes.BinaryOp;
import engine.opcodes.BranchOp;
import engine.opcodes.CallOp;
import engine.opcodes.LoadConstOp;
import engine.opcodes.LoadLocalOp;
import engine.opcodes.Opcode;
import engine.opcodes.Operator;
import engine.opcodes.ReturnOp;
import engine.opcodes.StoreLocalOp;
import types.Method;

/**
 * Useful methods for testing.
 */
public class TestUtil {
    /**
     * Parses VM assembly into opcodes.
     *
     * @param scanner
     * @return
     */
    public static List<Opcode> parseOpcodes(String code) {
        String[] lines = code.split("\n");

        List<Opcode> ops = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] tokens = line.split(" ");

            if (tokens[0].endsWith(":")) {
                tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            }

            ops.add(TestUtil.createOpcode(tokens));
        }
        return ops;
    }

    /**
     * Creates an opcode.
     *
     * @param tokens the tokens for this opcode
     * @return the opcode
     */
    private static Opcode createOpcode(String[] tokens) {
        String opName = tokens[0];

        Operator type = Operator.fromInstruction(opName);
        if (type != null) {
            return new BinaryOp(type);
        }

        if ("load_const".equals(opName)) {
            return new LoadConstOp(Integer.parseInt(tokens[1]));
        }

        if ("load_local".equals(opName)) {
            return new LoadLocalOp(tokens[1]);
        }

        if ("store_local".equals(opName)) {
            return new StoreLocalOp(tokens[1]);
        }

        if ("branch".equals(opName)) {
            return new BranchOp(BranchOp.Type.UNCONDITIONAL, Integer.parseInt(tokens[1]));
        }

        if ("branchT".equals(opName)) {
            return new BranchOp(BranchOp.Type.TRUE, Integer.parseInt(tokens[1]));
        }

        if ("branchF".equals(opName)) {
            return new BranchOp(BranchOp.Type.FALSE, Integer.parseInt(tokens[1]));
        }

        if ("call".equals(opName)) {
            return new CallOp(tokens[1]);
        }

        if ("return".equals(opName)) {
            return new ReturnOp();
        }

        throw new RuntimeException("Unrecognized opcode: " + opName);
    }

    /**
     * Executes the supplied code, and returns the local variables.
     *
     * @param code the code
     * @return the local variables
     */
    public static Map<String, Integer> testCode(String code) {
        List<Opcode> ops = parseOpcodes(code);
        Method method = new Method("main", Collections.emptyList(), ops);
        CompiledClassCache.instance().saveMethod(method);
        VMThread e = new VMThread("main");
        return e.run();
    }

    public static void testPrintAST(Visitable root, String expectedCode) {
        PrintVisitor pv = new PrintVisitor();
        root.accept(pv);
        assertEquals("AST incorrect", expectedCode, pv.getResult());
    }

    /**
     * Turns opcodes back into VM assembly instructions.
     *
     * @param opcodes the opcodes
     * @return the disassembly
     */
    public static String disassemble(List<Opcode> opcodes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < opcodes.size(); i++) {
            Opcode op = opcodes.get(i);
            sb.append(i).append(": ").append(op).append('\n');
        }

        return sb.toString();
    }

    /**
     * Adds line numbers to the supplied code, if they're not already present.
     *
     * @param code the code
     * @return the code with line numbers
     */
    private static String addLineNumbers(String code) {
        String[] lines = code.split("\n");
        if (lines[0].indexOf(":") >= 0) {
            return code;
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i] = i + ": " + lines[i];
        }
        return String.join("\n", lines) + "\n";
    }

    /**
     * Runs the CompileVisitor over the supplied AST, and checks the disassembly.
     *
     * @param root                the AST
     * @param expectedDisassembly the expected disassembly
     */
    public static void testCompileAST(Visitable root, String expectedDisassembly) {
        CompileVisitor v = new CompileVisitor();
        root.accept(v);
        String actualDisassembly = TestUtil.disassemble(v.getOpcodes());
        expectedDisassembly = TestUtil.addLineNumbers(expectedDisassembly);
        assertEquals("generated code incorrect", expectedDisassembly, actualDisassembly);
    }

    /**
     * Gets a method from the CompiledClassCache, and checks its disassembly.
     *
     * @param methodName          the method name
     * @param expectedDisassembly the expected disassembly
     */
    public static void checkMethod(String methodName, String expectedDisassembly) {
        Method method = CompiledClassCache.instance().resolveMethod(methodName);
        assertNotNull("method not found", method);
        assertEquals("method name incorrect", methodName, method.getName());
        expectedDisassembly = TestUtil.addLineNumbers(expectedDisassembly);
        assertEquals("method opcodes incorrect", expectedDisassembly, TestUtil.disassemble(method.getOpcodes()));
    }
}
