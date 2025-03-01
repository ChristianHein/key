package de.uka.ilkd.key.java.expression.operator;

import org.key_project.util.ExtList;

import de.uka.ilkd.key.java.Expression;
import de.uka.ilkd.key.java.PrettyPrinter;
import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.java.abstraction.KeYJavaType;
import de.uka.ilkd.key.java.expression.Operator;
import de.uka.ilkd.key.java.reference.ExecutionContext;
import de.uka.ilkd.key.java.visitor.Visitor;


/**
 * Logical or.
 */

public class LogicalOr extends Operator {

    /**
     * Logical or.
     */

    public LogicalOr(ExtList children) {
        super(children);
    }


    public LogicalOr(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }


    /**
     * Get arity.
     *
     * @return the int value.
     */

    public int getArity() {
        return 2;
    }

    /**
     * Get precedence.
     *
     * @return the int value.
     */

    public int getPrecedence() {
        return 11;
    }

    /**
     * Get notation.
     *
     * @return the int value.
     */

    public int getNotation() {
        return INFIX;
    }

    /**
     * calls the corresponding method of a visitor in order to perform some action/transformation on
     * this element
     *
     * @param v the Visitor
     */
    public void visit(Visitor v) {
        v.performActionOnLogicalOr(this);
    }

    public void prettyPrint(PrettyPrinter p) throws java.io.IOException {
        p.printLogicalOr(this);
    }

    public KeYJavaType getKeYJavaType(Services services, ExecutionContext ec) {
        return services.getTypeConverter().getBooleanType();
    }
}
