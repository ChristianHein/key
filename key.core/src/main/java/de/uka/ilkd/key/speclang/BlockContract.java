package de.uka.ilkd.key.speclang;

import java.util.Map;
import java.util.function.UnaryOperator;

import org.key_project.util.collection.ImmutableList;

import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.java.StatementBlock;
import de.uka.ilkd.key.java.abstraction.KeYJavaType;
import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.op.IObserverFunction;
import de.uka.ilkd.key.logic.op.LocationVariable;
import de.uka.ilkd.key.util.InfFlowSpec;

/**
 * <p>
 * A block contract.
 * </p>
 *
 * <p>
 * When a block contract is encountered in an existing proof, a {@code BlockContract} is used. To
 * generate a new proof obligation for a block contract, use {@link FunctionalBlockContract}
 * instead.
 * </p>
 *
 * @author wacker, lanzinger
 */
public interface BlockContract extends AuxiliaryContract {

    /**
     *
     * @param newBlock the new block.
     * @param newPreconditions the new preconditions.
     * @param newPostconditions the new postconditions.
     * @param newModifiesClauses the new modifies clauses.
     * @param newInfFlowSpecs the new information flow specifications.
     * @param newVariables the new variables.
     * @param newMeasuredBy the new measured-by clause.
     * @return a new block contract with the specified attributes.
     */
    public BlockContract update(StatementBlock newBlock,
            Map<LocationVariable, Term> newPreconditions,
            Map<LocationVariable, Term> newFreePreconditions,
            Map<LocationVariable, Term> newPostconditions,
            Map<LocationVariable, Term> newFreePostconditions,
            Map<LocationVariable, Term> newModifiesClauses,
            final ImmutableList<InfFlowSpec> newInfFlowSpecs, Variables newVariables,
            Term newMeasuredBy);

    /**
     * @param newKJT the type containing the new target method.
     * @param newPM the new target method.
     * @return a new block contract equal to this one except that it belongs to a different target.
     */
    @Override
    public BlockContract setTarget(KeYJavaType newKJT, IObserverFunction newPM);

    /**
     * @param newBlock the new block.
     * @return a new block contract equal to this one except that it belongs to a different block.
     */
    @Override
    public BlockContract setBlock(StatementBlock newBlock);

    @Override
    public BlockContract map(UnaryOperator<Term> op, Services services);

    /**
     *
     * @return the {@code LoopContract} from which this contract was generated, or {@code null}.
     * @see LoopContract#toBlockContract()
     */
    public LoopContract toLoopContract();
}
