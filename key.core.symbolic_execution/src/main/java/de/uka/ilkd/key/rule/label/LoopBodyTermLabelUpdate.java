package de.uka.ilkd.key.rule.label;

import java.util.Set;

import org.key_project.util.collection.ImmutableArray;
import org.key_project.util.collection.ImmutableList;
import org.key_project.util.collection.ImmutableSLList;

import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.logic.JavaBlock;
import de.uka.ilkd.key.logic.Name;
import de.uka.ilkd.key.logic.PosInOccurrence;
import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.label.TermLabel;
import de.uka.ilkd.key.logic.label.TermLabelState;
import de.uka.ilkd.key.logic.op.Operator;
import de.uka.ilkd.key.logic.op.QuantifiableVariable;
import de.uka.ilkd.key.rule.Rule;
import de.uka.ilkd.key.rule.RuleApp;
import de.uka.ilkd.key.rule.WhileInvariantRule;
import de.uka.ilkd.key.symbolic_execution.util.SymbolicExecutionUtil;

/**
 * Makes sure that {@link SymbolicExecutionUtil#LOOP_BODY_LABEL} is introduced when a
 * {@link WhileInvariantRule} is applied.
 *
 * @author Martin Hentschel
 */
public class LoopBodyTermLabelUpdate implements TermLabelUpdate {
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Name> getSupportedRuleNames() {
        return ImmutableSLList.<Name>nil().append(WhileInvariantRule.INSTANCE.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLabels(TermLabelState state, Services services,
            PosInOccurrence applicationPosInOccurrence, Term applicationTerm, Term modalityTerm,
            Rule rule, RuleApp ruleApp, Object hint, Term tacletTerm, Operator newTermOp,
            ImmutableArray<Term> newTermSubs, ImmutableArray<QuantifiableVariable> newTermBoundVars,
            JavaBlock newTermJavaBlock, Set<TermLabel> labels) {
        if (rule instanceof WhileInvariantRule && "LoopBodyModality".equals(hint)
                && SymbolicExecutionUtil.hasSymbolicExecutionLabel(modalityTerm)) {
            if (!labels.contains(SymbolicExecutionUtil.LOOP_BODY_LABEL)) {
                labels.add(SymbolicExecutionUtil.LOOP_BODY_LABEL);
            }
        }
    }
}
