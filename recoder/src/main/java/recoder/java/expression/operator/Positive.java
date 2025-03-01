// This file is part of the RECODER library and protected by the LGPL.

package recoder.java.expression.operator;

import recoder.java.Expression;
import recoder.java.SourceVisitor;
import recoder.java.expression.Operator;

/**
 * Positive.
 *
 * @author <TT>AutoDoc</TT>
 */

public class Positive extends Operator {

    /**
     * serialization id
     */
    private static final long serialVersionUID = 1940491451479434738L;

    /**
     * Positive.
     */

    public Positive() {
        // nothing to do
    }

    /**
     * Positive.
     *
     * @param child an expression.
     */

    public Positive(Expression child) {
        super(child);
        makeParentRoleValid();
    }

    /**
     * Positive.
     *
     * @param proto a positive.
     */

    protected Positive(Positive proto) {
        super(proto);
        makeParentRoleValid();
    }

    /**
     * Deep clone.
     *
     * @return the object.
     */

    public Positive deepClone() {
        return new Positive(this);
    }

    /**
     * Get arity.
     *
     * @return the int value.
     */

    public int getArity() {
        return 1;
    }

    /**
     * Get precedence.
     *
     * @return the int value.
     */

    public int getPrecedence() {
        return 1;
    }

    /**
     * Get notation.
     *
     * @return the int value.
     */

    public int getNotation() {
        return PREFIX;
    }

    /**
     * Checks if this operator is left or right associative. Ordinary unary operators are right
     * associative.
     *
     * @return <CODE>true</CODE>, if the operator is left associative, <CODE>
     * false</CODE> otherwise.
     */

    public boolean isLeftAssociative() {
        return false;
    }

    public void accept(SourceVisitor v) {
        v.visitPositive(this);
    }
}
