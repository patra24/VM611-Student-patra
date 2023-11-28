package ast;

import ast.model.ArgumentList;
import ast.model.CompoundStatement;
import ast.model.Expression;
import ast.model.IfStatement;
import ast.model.MethodCallExpression;
import ast.model.MethodDefinition;
import ast.model.ParameterDefinition;
import ast.model.ParameterList;
import ast.model.ReturnStatement;
import ast.model.Statement;
import ast.model.WhileStatement;
import engine.opcodes.Operator;
import types.DataType;

public class Parser {
    private Tokenizer tokens;

    public Parser(String code) {
        tokens = new Tokenizer(code);
    }

    /**
     * Parses an Operator
     *
     * @return the Operator
     */
    public Operator parseOperator() {
        // operator ::= '+' | '-' | '*' | '/' | '%' | '==' | '!=' | '>' | '>=' | '<' |
        // '<='
        return null;
    }

    /**
     * Parses arguments, e.g. "()" or "(x, 2, y)".
     *
     * @return the ArgumentList
     */
    public ArgumentList parseArguments() {
        // arguments ::= '(' (expression (',' expression)*)? ')'
        return null;
    }

    /**
     * Parses a method call expression, e.g. "someMethod(x, y)"
     *
     * @return the MethodCallExpression
     */
    public MethodCallExpression parseMethodCallExpression() {
        // method_call_expression ::= identifier arguments
        return null;
    }

    /**
     * Parses an expression.
     *
     * @return the Expression
     */
    public Expression parseExpression() {
        // @formatter:off
        // expression ::=
        //   '(' expression operator expression ')' |
        //   method_call_expression |
        //   variable |
        //   constant
        // @formatter:on
        return null;
    }

    /**
     * Parses an expression statement.
     *
     * @return the Statement
     */
    public Statement parseExpressionStatement() {
        // expression_statement ::= (expression '=')? expression ';'
        return null;
    }

    /**
     * Parses an if statement.
     *
     * @return the IfStatement
     */
    public IfStatement parseIfStatement() {
        // @formatter:off
        // if_statement ::=
        // 'if' expression compound_statement ('else' compound_statement)?
        // @formatter:on
        return null;
    }

    /**
     * Parses a while statement.
     *
     * @return the WhileStatement
     */
    public WhileStatement parseWhileStatement() {
        // while_statement ::= 'while' expression compound_statement
        return null;
    }

    /**
     * Parses a return statement.
     *
     * @return the ReturnStatement
     */
    public ReturnStatement parseReturnStatement() {
        // return_statement ::= 'return' expression? ';'
        return null;
    }

    /**
     * Parses a simple statement.
     *
     * @return the Statement
     */
    public Statement parseSimpleStatement() {
        // @formatter:off
        // simple_statement ::=
        //   if_statement |
        //   while_statement |
        //   return_statement |
        //   expression_statement
        // @formatter:on
        return null;
    }

    /**
     * Parses a compound statement.
     *
     * @return the CompoundStatement
     */
    public CompoundStatement parseCompoundStatement() {
        // compound_statement ::= '{' simple_statement+ '}'
        return null;
    }

    /**
     * Parses a data type.
     *
     * @return the DataType
     */
    public DataType parseDataType() {
        // data_type ::= 'int'
        return null;
    }

    /**
     * Parses a return type.
     * 
     * @return the DataType
     */
    public DataType parseReturnType() {
        // return_type ::= 'void' | data_type
        return null;
    }

    /**
     * Parses a parameter definition.
     *
     * @return the ParameterDefinition
     */
    public ParameterDefinition parseParameter() {
        // parameter ::= data_type identifier
        return null;
    }

    /**
     * Parses a parameter list, e.g. "()" or "(int x, int y)".
     *
     * @return the ParamaterList
     */
    public ParameterList parseParameters() {
        // parameters ::= '(' (parameter (',' parameter)*)? ')'
        return null;
    }

    /**
     * Parses a method definition.
     *
     * @return the MethodDefinition
     */
    public MethodDefinition parseMethod() {
        // method ::= return_type identifier parameters compound_statement
        return null;
    }
}
