package ast.visitors;

import ast.model.ArgumentList;
import ast.model.AssignStatement;
import ast.model.BinaryExpression;
import ast.model.CompoundStatement;
import ast.model.ConstantExpression;
import ast.model.ExpressionStatement;
import ast.model.IfStatement;
import ast.model.MethodCallExpression;
import ast.model.MethodDefinition;
import ast.model.ParameterDefinition;
import ast.model.ParameterList;
import ast.model.ReturnStatement;
import ast.model.VariableExpression;
import ast.model.WhileStatement;

/**
 * Interface for visitors.
 */
public interface Visitor {
    void visit(ConstantExpression expr);

    void visit(VariableExpression expr);

    void preVisit(BinaryExpression expr);

    void visit(BinaryExpression expr);

    void postVisit(BinaryExpression expr);

    void preVisit(ArgumentList args);

    void betweenArgumentVisit(ArgumentList args);

    void postVisit(ArgumentList args);

    void preVisit(MethodCallExpression expr);

    void postVisit(MethodCallExpression expr);

    void preVisit(AssignStatement stmt);

    void visit(AssignStatement stmt);

    void postVisit(AssignStatement stmt);

    void preVisit(WhileStatement stmt);

    void preBodyVisit(WhileStatement stmt);

    void postVisit(WhileStatement stmt);

    void preVisit(IfStatement stmt);

    void preThenVisit(IfStatement stmt);

    void preElseVisit(IfStatement stmt);

    void postVisit(IfStatement stmt);

    void preVisit(ReturnStatement stmt);

    void postVisit(ReturnStatement stmt);

    void postVisit(ExpressionStatement stmt);

    void preVisit(CompoundStatement stmt);

    void postVisit(CompoundStatement stmt);

    void visit(ParameterDefinition param);

    void preVisit(ParameterList params);

    void betweenParameterVisit(ParameterList params);

    void postVisit(ParameterList params);

    void preVisit(MethodDefinition method);

    void preBodyVisit(MethodDefinition method);

    void postVisit(MethodDefinition method);

}
