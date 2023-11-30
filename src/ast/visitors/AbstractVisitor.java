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
 * Convenience class with stubbed visit methods.
 */
public abstract class AbstractVisitor implements Visitor {

    @Override
    public void visit(ConstantExpression expr) {
    }

    @Override
    public void visit(VariableExpression expr) {
    }

    @Override
    public void preVisit(BinaryExpression expr) {
    }

    @Override
    public void visit(BinaryExpression expr) {
    }

    @Override
    public void postVisit(BinaryExpression expr) {
    }

    @Override
    public void preVisit(ArgumentList args) {
    }

    @Override
    public void betweenArgumentVisit(ArgumentList args) {
    }

    @Override
    public void postVisit(ArgumentList args) {
    }

    @Override
    public void preVisit(MethodCallExpression expr) {
    }

    @Override
    public void postVisit(MethodCallExpression expr) {
    }

    @Override
    public void preVisit(AssignStatement stmt) {
    }

    @Override
    public void visit(AssignStatement stmt) {
    }

    @Override
    public void postVisit(AssignStatement stmt) {
    }

    @Override
    public void preVisit(WhileStatement stmt) {
    }

    @Override
    public void preBodyVisit(WhileStatement stmt) {
    }

    @Override
    public void postVisit(WhileStatement stmt) {
    }

    @Override
    public void preVisit(IfStatement stmt) {
    }

    @Override
    public void preThenVisit(IfStatement stmt) {
    }

    @Override
    public void preElseVisit(IfStatement stmt) {
    }

    @Override
    public void postVisit(IfStatement stmt) {
    }

    @Override
    public void preVisit(ReturnStatement stmt) {
    }

    @Override
    public void postVisit(ReturnStatement stmt) {
    }

    @Override
    public void postVisit(ExpressionStatement stmt) {
    }

    @Override
    public void preVisit(CompoundStatement stmt) {
    }

    @Override
    public void postVisit(CompoundStatement stmt) {
    }

    @Override
    public void visit(ParameterDefinition param) {
    }

    @Override
    public void preVisit(ParameterList params) {
    }

    @Override
    public void betweenParameterVisit(ParameterList params) {
    }

    @Override
    public void postVisit(ParameterList params) {
    }

    @Override
    public void preVisit(MethodDefinition method) {
    }

    @Override
    public void preBodyVisit(MethodDefinition method) {
    }

    @Override
    public void postVisit(MethodDefinition method) {
    }

}
