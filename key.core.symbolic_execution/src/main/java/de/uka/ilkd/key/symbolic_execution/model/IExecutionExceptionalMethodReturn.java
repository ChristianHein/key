package de.uka.ilkd.key.symbolic_execution.model;

import de.uka.ilkd.key.java.statement.Throw;
import de.uka.ilkd.key.symbolic_execution.SymbolicExecutionTreeBuilder;
import de.uka.ilkd.key.symbolic_execution.model.impl.ExecutionExceptionalMethodReturn;

/**
 * <p>
 * A node in the symbolic execution tree which represents a exceptional method return.
 * </p>
 * <p>
 * The default implementation is {@link ExecutionExceptionalMethodReturn} which is instantiated via
 * a {@link SymbolicExecutionTreeBuilder} instance.
 * </p>
 *
 * @author Martin Hentschel
 * @see SymbolicExecutionTreeBuilder
 * @see ExecutionExceptionalMethodReturn
 */
public interface IExecutionExceptionalMethodReturn extends IExecutionBaseMethodReturn<Throw> {
}
