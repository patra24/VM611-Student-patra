package ast.visitors;

import ast.model.AssignStatement;
import ast.model.BinaryExpression;
import ast.model.IfStatement;
import ast.model.VariableExpression;
import ast.model.WhileStatement;

public class OptimizingVisitor extends CloneVisitor {

    @Override
    public void visit(VariableExpression expr) {
    }

    @Override
    public void postVisit(BinaryExpression expr) {
    }

    @Override
    public void postVisit(AssignStatement stmt) {
    }

    @Override
    public void postVisit(IfStatement stmt) {
    }

    @Override
    public void preVisit(WhileStatement stmt) {
    }

    @Override
    public void postVisit(WhileStatement stmt) {
    }

}
