import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ast.model.AssignStatement;
import ast.model.BinaryExpression;
import ast.model.CompoundStatement;
import ast.model.ConstantExpression;
import ast.model.Expression;
import ast.model.ExpressionStatement;
import ast.model.IfStatement;
import ast.model.MethodCallExpression;
import ast.model.MethodDefinition;
import ast.model.ParameterDefinition;
import ast.model.ReturnStatement;
import ast.model.Statement;
import ast.model.VariableExpression;
import ast.model.WhileStatement;
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

    private static void printAST(Object root, StringBuilder sb, String indentation) {
        switch (root) {
        case ConstantExpression e -> sb.append(e.getValue());
        case VariableExpression e -> sb.append(e.getName());
        case BinaryExpression e -> {
            sb.append('(');
            printAST(e.getLeftChild(), sb, indentation);
            sb.append(' ').append(e.getOperator()).append(' ');
            printAST(e.getRightChild(), sb, indentation);
            sb.append(')');
        }
        case AssignStatement s -> {
            sb.append(indentation);
            printAST(s.getLValue(), sb, indentation);
            sb.append(" = ");
            printAST(s.getValue(), sb, indentation);
            sb.append(";\n");
        }
        case CompoundStatement s -> {
            sb.append(" {\n");
            for (Statement st : s.getBody()) {
                printAST(st, sb, indentation + "  ");
            }
            sb.append(indentation).append("}");
        }
        case WhileStatement s -> {
            sb.append(indentation).append("while ");
            printAST(s.getCondition(), sb, indentation);
            printAST(s.getBody(), sb, indentation);
            sb.append('\n');
        }
        case IfStatement s -> {
            sb.append(indentation).append("if ");
            printAST(s.getCondition(), sb, indentation);
            printAST(s.getThenBlock(), sb, indentation);
            Statement elseBlock = s.getElseBlock();
            if (elseBlock != null) {
                sb.append(" else");
                printAST(elseBlock, sb, indentation);
            }
            sb.append('\n');
        }
        case ReturnStatement s -> {
            sb.append(indentation).append("return");
            Expression val = s.getReturnValue();
            if (val != null) {
                sb.append(' ');
                printAST(val, sb, indentation);
            }
            sb.append(";\n");
        }
        case ArrayList<?> l -> {
            sb.append('(');
            for (int i = 0; i < l.size(); i++) {
                printAST(l.get(i), sb, indentation);
                if (i < l.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(')');
        }
        case MethodCallExpression e -> {
            sb.append(e.getMethodName());
            printAST(e.getArguments(), sb, indentation);
        }
        case ExpressionStatement s -> {
            sb.append(indentation);
            printAST(s.getExpression(), sb, indentation);
            sb.append(";\n");
        }
        case ParameterDefinition p -> {
            sb.append(p.getType()).append(' ').append(p.getName());
        }
        case MethodDefinition m -> {
            sb.append(indentation).append(m.getReturnType()).append(' ').append(m.getName());
            printAST(m.getParameters(), sb, indentation);
            printAST(m.getBody(), sb, indentation);
            sb.append('\n');
        }
        default -> throw new RuntimeException("Unknown type: " + root);
        }
    }

    public static void testPrintAST(Object root, String expectedCode) {
        StringBuilder sb = new StringBuilder(500);
        printAST(root, sb, "");
        assertEquals("AST incorrect", expectedCode, sb.toString());
    }
}
